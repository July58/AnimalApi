package com.example.test_task.service.impl;

import com.example.test_task.dto.AnimalDto;
import com.example.test_task.dto.AnimalTransformer;
import com.example.test_task.exeption.NullEntityReferenceException;
import com.example.test_task.model.Animal;
import com.example.test_task.model.Category;
import com.example.test_task.repository.AnimalRepository;
import com.example.test_task.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    public AnimalServiceImpl(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }


    @Override
    public boolean validateAnimal(AnimalDto animaldto) {
        return (animaldto.getName() != null && !animaldto.getName().isEmpty()) &&
                (animaldto.getType() != null && !animaldto.getType().isEmpty()) &&
                (animaldto.getSex() != null && !animaldto.getSex().isEmpty()) &&
                (animaldto.getWeight() != null && !animaldto.getWeight().isEmpty()) &&
                (animaldto.getCost() != null && !animaldto.getCost().isEmpty());
    }

    @Override
    public List<Animal> createAnimal(List<AnimalDto> animalDtoList) {
        List<Animal> animals = new ArrayList<>();
        for (AnimalDto animalDto : animalDtoList) {
            if (validateAnimal(animalDto)) {
                Animal animal = AnimalTransformer.convertToEntity(animalDto);
                Category animalCategory = setAnimalCategory(animal);
                animal.setCategory(animalCategory);
                try {
                    animalRepository.save(animal);
                    animals.add(animal);
                } catch (IllegalArgumentException e) {
                    throw new NullEntityReferenceException("Couldn't be null");
                }
            }
        }
        return animals;
    }


    @Override
    public Category setAnimalCategory(Animal animal) {
        if (animal.getCost() > 0 && animal.getCost() <= 20) {
            return Category.I;
        } else if (animal.getCost() > 20 && animal.getCost() <= 40) {
            return Category.II;
        } else if (animal.getCost() > 40 && animal.getCost() <= 60) {
            return Category.III;
        } else if (animal.getCost() > 60) {
            return Category.IV;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    public List<AnimalDto> filterAnimals(Map<String, String> filters) {
        Predicate<Animal> filterPredicate = createFilterPredicate(filters);
        return getAllAnimals().stream()
                .filter(filterPredicate)
                .map(AnimalTransformer::convertToDto)
                .collect(Collectors.toList());
    }

    private Predicate<Animal> createFilterPredicate(Map<String, String> filters) {
        Predicate<Animal> predicate = animal -> true;

        if (filters.containsKey("type")) {
            String type = filters.get("type").toLowerCase();
            predicate = predicate.and(animal -> animal.getType().toString().toLowerCase().equals(type));
        }
        if (filters.containsKey("category")) {
            String category = filters.get("category").toLowerCase();
            predicate = predicate.and(animal -> animal.getCategory().toString().toLowerCase().equals(category));
        }
        if (filters.containsKey("sex")) {
            String sex = filters.get("sex").toLowerCase();
            predicate = predicate.and(animal -> animal.getSex().toString().toLowerCase().equals(sex));
        }

        return predicate;
    }

    @Override
    public List<AnimalDto> sortBy(String parameter, List<AnimalDto> animalDtoList) {
        List<Animal> animals = new ArrayList<>();
        if (animalDtoList != null) {
            animals = animalDtoList.stream().map(AnimalTransformer::convertToEntity)
                    .collect(Collectors.toList());
        } else {
            animals = getAllAnimals();
        }
        switch (parameter) {
            case "name" -> animals.sort(Comparator.comparing(Animal::getName));
            case "type" -> animals.sort(Comparator.comparing(Animal::getType));
            case "sex" -> animals.sort(Comparator.comparing(Animal::getSex));
            case "weight" -> animals.sort(Comparator.comparing(Animal::getWeight));
            case "cost" -> animals.sort(Comparator.comparing(Animal::getCost));
            case "category" -> animals.sort(Comparator.comparing(Animal::getCategory));
            default -> throw new IllegalArgumentException("Unsupported sorting field: " + parameter);
        }
        return animals.stream()
                .map(AnimalTransformer::convertToDto)
                .collect(Collectors.toList());
    }
}
