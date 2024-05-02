package com.example.test_task.mapper;

import com.example.test_task.dto.AnimalDto;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.Assert.assertEquals;


@SpringBootTest
public class CVSMapperTest {

    @Test
    public void testReadAnimalDtoFromCsv() {

        List<AnimalDto> animalDtoList = CVSMapper.readAnimalDtoFromCsv("src\\main\\resources\\uploads\\animals.csv");

        assertEquals(10, animalDtoList.size());

        AnimalDto animalDto1 = animalDtoList.get(0);
        assertEquals("Buddy", animalDto1.getName());
        assertEquals("cat", animalDto1.getType());
        assertEquals("female", animalDto1.getSex());
        assertEquals("41", animalDto1.getWeight());
        assertEquals("78", animalDto1.getCost());
    }
}