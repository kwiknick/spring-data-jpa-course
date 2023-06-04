package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
public class Student {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer age;
}
