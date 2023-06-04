package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            StudentEntity maria = new StudentEntity(
                    "Maria",
                    "Jones",
                    "maria.jones@willard.io",
                    41
            );

            StudentEntity maria2 = new StudentEntity(
                    "Maria",
                    "Jones",
                    "maria2.jones@willard.io",
                    25
            );

            StudentEntity nick = new StudentEntity(
                    "Nick",
                    "Willard",
                    "nick.willard@willard.io",
                    40
            );

            System.out.println("Adding maria and nick to the Database");
            studentRepository.saveAll(List.of(maria, nick, maria2));

            studentRepository
                    .findStudentByEmailCustomQuery("nick.willard@willard.io")
                    .ifPresentOrElse(
                            System.out::println,
                            () -> System.out.println("Student with email nick.willard@willard.io not found"));

            studentRepository
                    .findStudentByEmail("nick.willard@willard.io")
                    .ifPresentOrElse(
                            System.out::println,
                            () -> System.out.println("Student with email nick.willard@willard.io not found"));

            studentRepository
                    .findStudentsByFirstNameEqualsAndAgeIsGreaterThanCustomQuery(
                            "Maria",
                            18
                    ).forEach(System.out::println);

            studentRepository
                    .findStudentsByFirstNameEqualsAndAgeIsGreaterThan(
                            "Maria",
                            18
                    ).forEach(System.out::println);

            studentRepository
                    .findStudentsByFirstNameEqualsAndAgeIsGreaterOrEqualNativeQuery(
                            "Maria",
                            18
                    ).forEach(System.out::println);

            studentRepository
                    .findStudentsByFirstNameEqualsAndAgeIsGreaterOrEqualNamedParameters(
                            "Maria",
                            18
                    ).forEach(System.out::println);

            System.out.println("Deleting Maria #2");
            System.out.println(studentRepository.deleteStudentById(3L));
        };
    }
}
