package com.example.test_task.model;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Table(name = "files")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class FileData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "type")
    private String type;

    @NotNull
    @Column(name = "size")
    private Long size;

    @NotNull
    @Column(name = "date")
    private LocalDateTime date;

    @NotBlank
    @Column(name = "path")
    private String path;

}
