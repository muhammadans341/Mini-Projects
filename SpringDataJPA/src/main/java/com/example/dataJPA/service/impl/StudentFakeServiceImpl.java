package com.example.dataJPA.service.impl;

import com.example.dataJPA.entity.Student;
import com.example.dataJPA.model.StudentDTO;
import com.example.dataJPA.repository.StudentRepository;
import com.example.dataJPA.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class StudentFakeServiceImpl implements StudentService {

    @Autowired
    public StudentRepository studentRepository;

    @Autowired
    private ApplicationContext context;
    @Override
    public void addStudent(StudentDTO studentDTO) {
        Student entity = new Student();
        entity.setFirstName(studentDTO.getFirstName());
        entity.setLastName(studentDTO.getLastName());
        entity.setAge(studentDTO.getAge());
        entity.setEmail(studentDTO.getEmail());

        studentRepository.save(entity);
    }

    @Override
    public StudentDTO fetchStudentEmail(StudentDTO studentDTO) {
        return null;
    }
}
