package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            ApplicationHelper.generateRandomStudents(20, studentRepository);
            ApplicationHelper.retrieveStudentsSorted(studentRepository);

            PageRequest pageRequest = PageRequest.of(0, 5, Sort.by("firstName").ascending());
            Page<StudentEntity> page = studentRepository.findAll(pageRequest);
            System.out.println(page);

            for (int i = pageRequest.getPageNumber() + 1; i < page.getTotalPages(); i++) {
                pageRequest = PageRequest.of(i, pageRequest.getPageSize(), pageRequest.getSort());
                page = studentRepository.findAll(pageRequest);
                System.out.println(page);
                Thread.sleep(2000);
            }
        };
    }
}
