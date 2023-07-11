package com.example.manyToMany.Repositories;


import com.example.manyToMany.Entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
