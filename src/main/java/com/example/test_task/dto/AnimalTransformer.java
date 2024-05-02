package com.example.test_task.dto;

import com.example.test_task.model.Animal;
import com.example.test_task.model.Category;
import com.example.test_task.model.Sex;
import com.example.test_task.model.Type;

public class AnimalTransformer {

    public static AnimalDto convertToDto(Animal animal) {
        return new AnimalDto(
                animal.getId(),
                animal.getName(),
                animal.getType().toString().toLowerCase(),
                animal.getSex().toString().toLowerCase(),
                animal.getWeight().toString().toLowerCase(),
                animal.getCost().toString().toLowerCase(),
                animal.getCategory().toString()

        );
    }

    public static Animal convertToEntity(AnimalDto animalDto) {
        Animal animal = new Animal();
        animal.setId(animalDto.getId());
        animal.setName(animalDto.getName());
        animal.setType(Type.valueOf(animalDto.getType().toUpperCase()));
        animal.setSex(Sex.valueOf(animalDto.getSex().toUpperCase()));
        animal.setWeight(Integer.valueOf(animalDto.getWeight()));
        animal.setCost(Integer.valueOf(animalDto.getCost()));
        String categoryString = animalDto.getCategory();
        if (categoryString != null) {
            animal.setCategory(Category.valueOf(categoryString.toUpperCase()));
        }
        return animal;
    }
}
