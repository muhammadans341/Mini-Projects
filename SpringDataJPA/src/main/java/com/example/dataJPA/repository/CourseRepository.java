package com.example.dataJPA.repository;

import com.example.dataJPA.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long> {
    Course findByName(String name);
}
