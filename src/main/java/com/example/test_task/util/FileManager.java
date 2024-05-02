package com.example.test_task.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {
    private static final String DIRECTORY_PATH = "src\\main\\resources\\uploads";

    public static String getFileExtension(String name) {
        if (name.lastIndexOf(".") != -1 && name.lastIndexOf(".") != 0) {
            return name.substring(name.lastIndexOf(".") + 1);

        } else {
            return "";

        }
    }

    public static Path uploadFile(byte[] resource, String fileName) throws IOException {
        Path path = Paths.get(DIRECTORY_PATH, fileName);
        Files.write(path, resource);
        return path;
    }
    }
