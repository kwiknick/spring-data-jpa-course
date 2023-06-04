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

            StudentEntity nick = new StudentEntity(
                    "Nick",
                    "Willard",
                    "nick.willard@willard.io",
                    40
            );

            System.out.println("Adding maria and nick to the Database");
            studentRepository.saveAll(List.of(maria, nick));

            System.out.print("Number of Students: ");
            System.out.println(studentRepository.count());

            studentRepository
                    .findById(2L)
                    .ifPresentOrElse(
                            System.out::println,
                            () -> System.out.println("Student with ID 2 not found"));

            studentRepository
                    .findById(3L)
                    .ifPresentOrElse(
                            System.out::println,
                            () -> System.out.println("Student with ID 3 not found"));

            System.out.println("Select all Students");
            List<StudentEntity> students = studentRepository.findAll();
            students.forEach(System.out::println);

            System.out.println("Delete maria");
            studentRepository.deleteById(1L);

            System.out.print("Number of Students: ");
            System.out.println(studentRepository.count());
        };
    }
}
