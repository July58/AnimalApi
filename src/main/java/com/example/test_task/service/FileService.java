package com.example.test_task.service;

import com.example.test_task.dto.AnimalDto;
import com.example.test_task.model.FileData;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface FileService {

    FileData createFile(MultipartFile file) throws IOException;

    List<AnimalDto> convertToList(FileData file) throws JAXBException;
}
