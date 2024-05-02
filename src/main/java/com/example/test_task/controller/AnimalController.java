package com.example.test_task.controller;

import com.example.test_task.dto.AnimalDto;
import com.example.test_task.dto.AnimalResponse;
import com.example.test_task.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AnimalController {


    @Autowired
    AnimalService animalService;


    @GetMapping("/animals")
    public List<AnimalResponse> filterAndSort(@RequestParam(required = false) Map<String, String> filters, @RequestParam(required = false) String sort)
    {
        List<AnimalDto> animalResponses = new ArrayList<>();
        if(filters != null){
            animalResponses = animalService.filterAnimals(filters);
        }
        if(sort!=null){
            try{
            animalResponses = animalService.sortBy(sort, animalResponses);}
            catch (IllegalArgumentException e){
                return animalResponses.stream().map(AnimalResponse::new).collect(Collectors.toList());
            }
        }
        return animalResponses.stream().map(AnimalResponse::new).collect(Collectors.toList());
    }

}
