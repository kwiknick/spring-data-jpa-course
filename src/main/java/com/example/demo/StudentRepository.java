package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    Optional<StudentEntity> findStudentByEmail(String email);
    List<StudentEntity> findStudentsByFirstNameEqualsAndAgeEquals(String firstName, Integer age);
    List<StudentEntity> findStudentsByFirstNameEqualsAndAgeIsGreaterThan(String firstName, Integer age);

    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<StudentEntity> findStudentByEmailCustomQuery(String email);

    @Query("SELECT s FROM Student s WHERE s.firstName = ?1 AND s.age >= ?2")
    List<StudentEntity> findStudentsByFirstNameEqualsAndAgeIsGreaterThanCustomQuery(String firstName, Integer age);
}
