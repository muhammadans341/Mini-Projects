package com.example.oneToMany.Repositories;


import com.example.oneToMany.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
