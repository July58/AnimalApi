package com.example.test_task.dto;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Value;

@Value
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AnimalResponse {

    String name;

    String type;

    String sex;

    String weight;

    String cost;

    String category;


    public AnimalResponse(AnimalDto animalDto) {
        this.name = animalDto.getName();
        this.type = animalDto.getType();
        this.sex = animalDto.getSex();
        this.weight = animalDto.getWeight();
        this.cost = animalDto.getCost();
        this.category = animalDto.getCategory();
    }
}
