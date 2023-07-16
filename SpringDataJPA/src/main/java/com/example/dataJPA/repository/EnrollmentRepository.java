package com.example.dataJPA.repository;

import com.example.dataJPA.entity.Enrollment;
import com.example.dataJPA.entity.EnrollmentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentId> {
}
