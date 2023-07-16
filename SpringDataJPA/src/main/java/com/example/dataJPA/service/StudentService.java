package com.example.dataJPA.service;

import com.example.dataJPA.model.StudentDTO;
import org.springframework.stereotype.Service;

@Service
public interface StudentService {
    public void addStudent(StudentDTO studentDTO);
    public StudentDTO fetchStudentEmail(StudentDTO studentDTO);
}
