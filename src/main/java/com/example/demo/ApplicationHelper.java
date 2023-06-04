package com.example.demo;

import com.github.javafaker.Faker;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class ApplicationHelper {

    public static Integer generateRandomStudents(Integer rowsToCreate, StudentRepository studentRepository) {
        Integer savedStudents = 0;
        Faker faker = new Faker();
        for (int i = 1; i <= 20; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = String.format("%s.%s@willard.io", firstName, lastName);
            StudentEntity student = new StudentEntity(
                    firstName,
                    lastName,
                    email,
                    faker.number().numberBetween(17, 55));

            try {
                studentRepository.save(student);
                savedStudents = savedStudents + 1;
            } catch (Exception ex) {
                System.out.println("An Exception was thrown while saving a test Student: " + ex.getMessage());
            }
        }
        return savedStudents;
    }

    public static void retrieveStudentsSorted(StudentRepository studentRepository) {
        Sort sort = Sort.by(Sort.Direction.ASC, "firstName")
                .and(Sort.by("age").descending());
        studentRepository.findAll(sort)
                .forEach(student -> System.out.println(student.getFirstName() + " " + student.getAge()));
    }
}
