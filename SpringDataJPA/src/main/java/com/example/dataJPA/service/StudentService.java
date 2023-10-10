package com.example.dataJPA.service;

import com.example.dataJPA.model.StudentDTO;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public interface StudentService {
    public void addStudent(StudentDTO studentDTO);
    public StudentDTO fetchStudentEmail(StudentDTO studentDTO);

    public int testStudent();
}
