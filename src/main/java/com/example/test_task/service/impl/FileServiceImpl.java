package com.example.test_task.service.impl;

import com.example.test_task.dto.AnimalDto;
import com.example.test_task.mapper.CVSMapper;
import com.example.test_task.mapper.XMLMapper;
import com.example.test_task.model.FileData;
import com.example.test_task.repository.FileRepository;
import com.example.test_task.service.FileService;
import com.example.test_task.util.FileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileRepository fileRepository;

    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }


    @Override
    public FileData createFile(MultipartFile file) throws IOException {
        FileData createdFile = new FileData();
        createdFile.setName(file.getOriginalFilename());
        createdFile.setType(FileManager.getFileExtension(Objects.requireNonNull(file.getOriginalFilename())).toLowerCase());
        createdFile.setSize(file.getSize());
        createdFile.setDate(LocalDateTime.now());

        Path path = FileManager.uploadFile(file.getBytes(), createdFile.getName());
        createdFile.setPath(path.toString());

        fileRepository.save(createdFile);
        return createdFile;
    }

    @Override
    public List<AnimalDto> convertToList(FileData file) throws JAXBException {
        List<AnimalDto> animals = new ArrayList<>();
        if (file.getType().equals("csv")){
            animals = CVSMapper.readAnimalDtoFromCsv(file.getPath());
            return animals;
        }else if(file.getType().equals("xml")){
            animals = XMLMapper.readAnimalsFromFile(file.getPath());
        }
    return animals;
    }


}
