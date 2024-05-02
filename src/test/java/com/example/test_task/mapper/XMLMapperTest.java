package com.example.test_task.mapper;

import com.example.test_task.dto.AnimalDto;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.bind.JAXBException;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class XMLMapperTest {

    @Test
    public void testReadAnimalDtoFromXML() throws JAXBException {

        List<AnimalDto> animalDtoList = XMLMapper.readAnimalsFromFile("src\\main\\resources\\uploads\\animals.xml");

        assertEquals(10, animalDtoList.size());

        AnimalDto animalDto1 = animalDtoList.get(0);
        assertEquals("Milo", animalDto1.getName());
        assertEquals("cat", animalDto1.getType());
        assertEquals("male", animalDto1.getSex());
        assertEquals("40", animalDto1.getWeight());
        assertEquals("51", animalDto1.getCost());
    }
}
