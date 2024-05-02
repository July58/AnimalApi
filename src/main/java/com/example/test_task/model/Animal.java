package com.example.test_task.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "animals")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "type")
    private Type type;

    @NotBlank
    @Column(name = "sex")
    private Sex sex;

    @NotNull
    @Column(name = "weight")
    private Integer weight;

    @NotNull
    @Column(name = "cost")
    private Integer cost;

    @Column(name = "category")
    private Category category;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(id, animal.id) && Objects.equals(name, animal.name) && type == animal.type && sex == animal.sex && Objects.equals(weight, animal.weight) && Objects.equals(cost, animal.cost) && category == animal.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, sex, weight, cost, category);
    }
}
