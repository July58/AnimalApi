package com.example.test_task.mapper;

import com.example.test_task.dto.AnimalDto;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.List;

public class XMLMapper {

    @XmlRootElement(name = "animals")
    private static class AnimalListDto {
        private List<AnimalDto> animals;

        @XmlElement(name = "animal")
        public List<AnimalDto> getAnimals() {
            return animals;
        }

        public void setAnimals(List<AnimalDto> animals) {
            this.animals = animals;
        }
    }

    public static List<AnimalDto> readAnimalsFromFile(String path) throws JAXBException {
        File file = new File(path);
        JAXBContext jaxbContext = JAXBContext.newInstance(AnimalListDto.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        AnimalListDto animalList = (AnimalListDto) jaxbUnmarshaller.unmarshal(file);

        return animalList.getAnimals();
    }
}