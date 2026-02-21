package com.rest.program.entity;

import com.rest.program.utils.AnimalTypeEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Table(name="animal")
@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Animal {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private AnimalTypeEnum type;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    public Animal(AnimalTypeEnum type, String name, int age) {
        this.type = type;
        this.name = name;
        this.age = age;
    }

}
