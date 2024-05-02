package com.example.test_task.repository;

import com.example.test_task.model.FileData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileData, Long> {
}