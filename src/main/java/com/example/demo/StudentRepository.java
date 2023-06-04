package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends PagingAndSortingRepository<StudentEntity, Long> {
    Optional<StudentEntity> findStudentByEmail(String email);
    List<StudentEntity> findStudentsByFirstNameEqualsAndAgeEquals(String firstName, Integer age);
    List<StudentEntity> findStudentsByFirstNameEqualsAndAgeIsGreaterThan(String firstName, Integer age);

    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<StudentEntity> findStudentByEmailCustomQuery(String email);

    @Query("SELECT s FROM Student s WHERE s.firstName = ?1 AND s.age >= ?2")
    List<StudentEntity> findStudentsByFirstNameEqualsAndAgeIsGreaterThanCustomQuery(String firstName, Integer age);

    @Query(value = "SELECT * FROM student WHERE first_name = ?1 AND age >= ?2", nativeQuery = true)
    List<StudentEntity> findStudentsByFirstNameEqualsAndAgeIsGreaterOrEqualNativeQuery(String firstName, Integer age);

    @Query(value = "SELECT * FROM student WHERE first_name = :firstName AND age >= :age", nativeQuery = true)
    List<StudentEntity> findStudentsByFirstNameEqualsAndAgeIsGreaterOrEqualNamedParameters(
            @Param("firstName") String firstName,
            @Param("age") Integer age);

    @Transactional
    @Modifying
    @Query("DELETE FROM Student u WHERE u.id = ?1")
    int deleteStudentById(Long id);
}
