package com.example.dataJPA.controller;

import com.example.dataJPA.model.StudentDTO;
import com.example.dataJPA.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class StudentController {
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    StudentService studentService;
    public StudentController(ApplicationContext applicationContext) {
        this.applicationContext=applicationContext;
    }

    @PostMapping("/api/addStudent")
    public StudentDTO addStudent(@RequestBody StudentDTO studentDTO) throws InterruptedException {
        return null;
        //studentService.addStudent(studentDTO);

        //return studentService.fetchStudentEmail(studentDTO);
//        ExecutorService executorService = Executors.newFixedThreadPool(200);
//
//        for (int i=0;i<200;i++) {
//            StudentDTO s = StudentDTO.builder().firstName("Fname"+i)
//                    .lastName("LastName"+i)
//                    .email("Fname"+i+".LastName"+i+"@gmail.com")
//                    .age(i).build();
//            Runnable runnable = () -> {
//                try {
//                    StudentService studentService=applicationContext.getBean(StudentService.class);
//                    studentService.addStudent(s);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            };
//            executorService.submit(runnable);
//        }
//
//        executorService.shutdown();
//        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
//
    }

    @PostMapping("/api/updateStudent")

    public int updateStudent() throws InterruptedException {
        StudentService studentService = applicationContext.getBean(StudentService.class);
       return studentService.testStudent();
    }

    @PostMapping("/api/greeting")
    public void greeting(){
        System.out.println("updating");
    }

}
