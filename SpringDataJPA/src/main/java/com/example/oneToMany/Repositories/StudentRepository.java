package com.example.oneToMany.Repositories;


import com.example.oneToMany.Entity.StudentTest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentTest, Long> {
}
