package com.example.dataJPA.repository;

import com.example.dataJPA.entity.StudentIDCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentIdCardRepository extends JpaRepository<StudentIDCard,Long> {
}
