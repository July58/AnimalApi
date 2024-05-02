package com.example.test_task.service;

import com.example.test_task.dto.AnimalDto;
import com.example.test_task.model.Animal;
import com.example.test_task.model.Category;

import java.util.List;
import java.util.Map;

public interface AnimalService {

    boolean validateAnimal(AnimalDto animalDto);
    List<Animal> createAnimal(List<AnimalDto> animalDtoList);

    Category setAnimalCategory(Animal animal);

    List<Animal> getAllAnimals();

    List<AnimalDto> filterAnimals(Map<String, String> filters);

    List<AnimalDto> sortBy(String parameter, List<AnimalDto> animalDtoList);
}
