package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    Optional<StudentEntity> findStudentByEmail(String email);
    List<StudentEntity> findStudentsByFirstNameEqualsAndAgeEquals(String firstName, Integer age);
    List<StudentEntity> findStudentsByFirstNameEqualsAndAgeIsGreaterThan(String firstName, Integer age);
}
