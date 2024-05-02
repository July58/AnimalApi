package com.example.test_task.validator;

import com.example.test_task.util.FileManager;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return MultipartFile.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MultipartFile file = (MultipartFile) target;

        if (file.getSize()==0) {
            errors.reject("file.empty", "File is empty");
        }

        String fileExtension = FileManager.getFileExtension(file.getOriginalFilename().toLowerCase());
        if(!fileExtension.equals("xml") && !fileExtension.equals("csv")){
            errors.reject("file.extension", "File has wrong extension");
        }
    }


}
