package com.example.test_task.controller;

import com.example.test_task.dto.AnimalDto;
import com.example.test_task.dto.AnimalResponse;
import com.example.test_task.model.FileData;
import com.example.test_task.service.AnimalService;
import com.example.test_task.service.FileService;
import com.example.test_task.validator.FileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.DirectFieldBindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private AnimalService animalService;

    @Autowired
    FileValidator fileValidator;


    @PostMapping("/files/uploads")
    public ResponseEntity<List<AnimalResponse>> upload(@RequestBody MultipartFile file) {
        Errors errors = new DirectFieldBindingResult(file, "file");
        fileValidator.validate(file, errors);
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        try {

            FileData createdFile = fileService.createFile(file);

            List<AnimalDto> animalDtoList = fileService.convertToList(createdFile);

            animalService.createAnimal(animalDtoList);

            return ResponseEntity.status(HttpStatus.CREATED).body(animalDtoList.stream().map(AnimalResponse::new).collect(Collectors.toList()));

        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }
}
