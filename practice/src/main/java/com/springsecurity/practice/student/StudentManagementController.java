package com.springsecurity.practice.student;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/management/v1/students")
public class StudentManagementController {

    private static final List<Student> students = Arrays.asList(
            new Student(1, "Yuta Okkotsu"),
            new Student(2, "Gojo Satoru"),
            new Student(3, "Itadori Yuji")
    );

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_TEACHER','ROLE_ASSISTANT_TEACHER')")
    public List<Student> getStudent(){
        return students;
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('teacher_write')")
    public Student addStudent(@RequestBody Student student){
         System.out.println(student);
        return student;
    }

    @DeleteMapping(path = "{studentId}")
    @PreAuthorize("hasAuthority('teacher_write')")
    public Integer deleteStudent(@PathVariable("studentId") Integer studentId){
        System.out.println(studentId);
        return studentId;
    }

    @PutMapping(path = "{studentId}")
    @PreAuthorize("hasAuthority('teacher:write')")
    public Integer updateStudent(@PathVariable("studentId") Integer studentId, @RequestBody Student student){
        System.out.println(studentId);
        return studentId;
    }

}
