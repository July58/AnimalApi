package com.example.test_task.service;

import com.example.test_task.dto.AnimalDto;
import com.example.test_task.model.Animal;
import com.example.test_task.model.Category;
import com.example.test_task.model.Sex;
import com.example.test_task.model.Type;
import com.example.test_task.repository.AnimalRepository;
import com.example.test_task.service.impl.AnimalServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;


import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AnimalServiceTest {


    @MockBean
    private AnimalRepository animalRepository;

    @InjectMocks
    private AnimalService animalService = new AnimalServiceImpl(animalRepository);


    @Test
    void validateAnimal_ValidAnimal() {
        AnimalDto animalDto = new AnimalDto(1L, "Milo", "cat", "male", "40", "50", null);
        assertTrue(animalService.validateAnimal(animalDto));
    }

    @Test
    void validateAnimal_NonValidAnimal() {
        AnimalDto animalDto = new AnimalDto(1L, null, "cat", "male", "40", "50", null);
        assertFalse(animalService.validateAnimal(animalDto));
    }

    @Test
    void setAnimalCategoryI_Test() {
        Animal animal = new Animal();
        animal.setCost(19);
        assertEquals(Category.I, animalService.setAnimalCategory(animal));
    }

    @Test
    void setAnimalCategoryII_Test() {
        Animal animal = new Animal();
        animal.setCost(30);
        assertEquals(Category.II, animalService.setAnimalCategory(animal));
    }

    @Test
    void setAnimalCategoryIII_Test() {
        Animal animal = new Animal();
        animal.setCost(41);
        assertEquals(Category.III, animalService.setAnimalCategory(animal));
    }

    @Test
    void setAnimalCategoryIV_Test() {
        Animal animal = new Animal();
        animal.setCost(62);
        assertEquals(Category.IV, animalService.setAnimalCategory(animal));
    }

    @Test
    void setAnimalCategory_WrongData() {
        Animal animal = new Animal();
        animal.setCost(-1);
        assertThrows(IllegalArgumentException.class, () -> animalService.setAnimalCategory(animal));
    }

    @Test
    void getAllAnimals_Test() {

        Animal animal1 = new Animal(1L, "Milo", Type.CAT, Sex.MALE, 40, 51, Category.I);
        Animal animal2 = new Animal(2L, "Simon", Type.DOG, Sex.MALE, 45, 17, Category.I);
        List<Animal> animals = Arrays.asList(animal1, animal2);

        when(animalRepository.findAll()).thenReturn(animals);

        List<Animal> result = animalService.getAllAnimals();

        assertEquals(2, result.size());
        assertTrue(result.contains(animal1));
        assertTrue(result.contains(animal2));
    }


    @Test
    void filterAnimals_Test() {
        Animal cat = new Animal(1L, "Milo", Type.CAT, Sex.MALE, 40, 51, Category.I);
        Animal dog = new Animal(2L, "Simon", Type.DOG, Sex.MALE, 45, 17, Category.II);
        List<Animal> animals = Arrays.asList(cat, dog);
        when(animalRepository.findAll()).thenReturn(animals);
        Map<String, String> filters = new HashMap<>();
        filters.put("type", "cat");
        List<AnimalDto> filteredAnimals = animalService.filterAnimals(filters);
        assertEquals(1, filteredAnimals.size());
    }


    @Test
    void sortBy_Test() {


        Animal cat = new Animal(1L, "Milo", Type.CAT, Sex.MALE, 40, 51, Category.I);
        Animal dog = new Animal(2L, "Simon", Type.DOG, Sex.MALE, 45, 17, Category.II);
        List<Animal> animals = Arrays.asList(cat, dog);


        when(animalRepository.findAll()).thenReturn(animals);

        List<AnimalDto> sortedAnimalsByName = animalService.sortBy("name", null);
        List<AnimalDto> sortedAnimalsByType = animalService.sortBy("type", null);
        List<AnimalDto> sortedAnimalsBySex = animalService.sortBy("sex", null);
        List<AnimalDto> sortedAnimalsByWeight = animalService.sortBy("weight", null);
        List<AnimalDto> sortedAnimalsByCost = animalService.sortBy("cost", null);
        List<AnimalDto> sortedAnimalsByCategory = animalService.sortBy("category", null);


        assertEquals("Milo", sortedAnimalsByName.get(0).getName());
        assertEquals("Simon", sortedAnimalsByName.get(1).getName());

        assertEquals("cat", sortedAnimalsByType.get(0).getType());
        assertEquals("dog", sortedAnimalsByType.get(1).getType());

        assertEquals("male", sortedAnimalsBySex.get(0).getSex());
        assertEquals("male", sortedAnimalsBySex.get(1).getSex());

        assertEquals(40, Integer.parseInt(sortedAnimalsByWeight.get(0).getWeight()));
        assertEquals(45, Integer.parseInt(sortedAnimalsByWeight.get(1).getWeight()));

        assertEquals(17, Integer.parseInt(sortedAnimalsByCost.get(0).getCost()));
        assertEquals(51, Integer.parseInt(sortedAnimalsByCost.get(1).getCost()));

        assertEquals("I", sortedAnimalsByCategory.get(0).getCategory());
        assertEquals("II", sortedAnimalsByCategory.get(1).getCategory());

    }

}
