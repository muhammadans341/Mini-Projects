package com.example.dataJPA.repository;

import com.example.dataJPA.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    @Query("SELECT s from Student s where s.email = ?1")
    Optional<Student> fetchStudentByEmail(String email);
    @Query("SELECT s from Student s where s.firstName = ?1 and s.age = ?2")
    Optional<Student> fingStudentByFirstNameAndAgeGreaterThanEqual(String firstName, Integer age);

    @Query("SELECT s from Student s where s.firstName = :firstName and s.age = :age")
    Optional<Student> fingStudentByFirstNameAndAgeGreaterThanEqualWithNamedParams(
            @Param("firstName") String firstName,@Param("age") Integer age);

    @Transactional
    @Modifying
    @Query("DELETE FROM Student s where s.email = :email")
    int deleteStudentByEmail(String email);
}
