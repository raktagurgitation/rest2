package com.rest.program.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;


@Slf4j
@Table(name="base")
@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Base {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "type")
    private String type;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    public Base(String type, String name, int age) {
        this.type = type;
        this.name = name;
        this.age = age;
    }

}
