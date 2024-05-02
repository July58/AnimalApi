package com.example.test_task.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@AllArgsConstructor
@XmlRootElement(name = "animal")
@ToString
@NoArgsConstructor
public class AnimalDto {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String type;

    @NotBlank
    private String sex;

    @NotNull
    private String weight;

    @NotNull
    private String cost;

    private String category;


    public void setId(Long id) {
        this.id = id;
    }

    @XmlElement(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "type")
    public void setType(String type) {
        this.type = type;
    }
    @XmlElement(name = "sex")
    public void setSex(String sex) {
        this.sex = sex;
    }
    @XmlElement(name = "weight")
    public void setWeight(String weight) {
        this.weight = weight;
    }
    @XmlElement(name = "cost")
    public void setCost(String cost) {
        this.cost = cost;
    }
}
