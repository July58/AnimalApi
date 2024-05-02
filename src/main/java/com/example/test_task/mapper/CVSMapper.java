package com.example.test_task.mapper;

import com.example.test_task.dto.AnimalDto;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CVSMapper {

    public static List<AnimalDto> readAnimalDtoFromCsv(String csvFile) {
        List<AnimalDto> animalList = new ArrayList<>();
        Map<String, Integer> headerMap = new HashMap<>();

        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            List<String[]> csvData = reader.readAll();

            String[] headerRow = csvData.get(0);

            for (int i = 0; i < headerRow.length; i++) {
                headerMap.put(headerRow[i].toLowerCase(), i);
            }

            for (int i = 1; i < csvData.size(); i++) {
                String[] row = csvData.get(i);
                AnimalDto animalDto = mapRowToAnimalDto(row, headerMap);
                if (animalDto != null) {
                    animalList.add(animalDto);
                }
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        return animalList;
    }

    public static AnimalDto mapRowToAnimalDto(String[] row, Map<String, Integer> headerMap) {
        AnimalDto animalDto = new AnimalDto();
        try {
            animalDto.setName(getValue(row, headerMap, "name"));
            animalDto.setType(getValue(row, headerMap, "type"));
            animalDto.setSex(getValue(row, headerMap, "sex"));
            animalDto.setWeight(getValue(row, headerMap, "weight"));
            animalDto.setCost(getValue(row, headerMap, "cost"));
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return null;
        }
        return animalDto;
    }

    private static String getValue(String[] row, Map<String, Integer> headerMap, String columnName) {
        Integer columnIndex = headerMap.get(columnName.toLowerCase());
        if (columnIndex != null && columnIndex < row.length) {
            return row[columnIndex];
        }
        return "";
    }

}



