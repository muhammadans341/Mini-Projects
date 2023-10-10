package com.example.dataJPA.service.impl;

import com.example.dataJPA.entity.*;
import com.example.dataJPA.model.StudentDTO;
import com.example.dataJPA.repository.CourseRepository;
import com.example.dataJPA.repository.StudentRepository;
import com.example.dataJPA.service.StudentService;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Primary
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class StudentServiceImpl implements StudentService {

    @Autowired
    public StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ApplicationContext context;

    private AtomicInteger result = new AtomicInteger(0);
    //private int result;
    @Override
    public void addStudent(StudentDTO studentDTO) {

        Course course = new Course();
        course.setDepartment("IT");
        course.setName("Spring data");

        Course course2 = new Course();
        course2.setDepartment("IT");
        course2.setName("Spring Boot");

        List<Course> courses = courseRepository.saveAll(Arrays.asList(course, course2));


        Faker faker = new Faker();
        Student student = new Student();

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();

        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(String.format("%s.%s@gmail.com",firstName,lastName));
        student.setAge(faker.number().numberBetween(17,55));

        student.addBook(Book.builder()
                .bookName("physics").build());

        student.addBook(Book.builder()
                .bookName("english").build());

        student.addBook(Book.builder()
                .bookName("math").build());

        student.setStudentIDCard(StudentIDCard
                .builder()
                .cardNumber("123456")
                .student(student)
                .build());

        Course course1 = courseRepository.findByName("Spring data");

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course1);

        student.getEnrollments().add(enrollment);

        studentRepository.save(student);
    }

    @Override
    public StudentDTO fetchStudentEmail(StudentDTO studentDTO) {
        Optional<Student> student =studentRepository.fetchStudentByEmail(studentDTO.getEmail());
        return student.map(value -> StudentDTO.builder()
                .firstName(value.getFirstName())
                .lastName(value.getLastName())
                .email(value.getEmail())
                .age((value.getAge()))
                .build()).orElse(null);

    }

    public int testStudent(){
        System.out.println("result:"+result);
        for(int i=0;i<1000000;i++){
            System.out.println("test");
            result.incrementAndGet();
        }
        for(int i=0;i<1000000;i++){
            System.out.println("test");
            result.decrementAndGet();
        }
        return result.get();
    }
}
