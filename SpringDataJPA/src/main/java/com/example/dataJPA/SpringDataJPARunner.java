package com.example.dataJPA;

import com.example.dataJPA.entity.*;
import com.example.dataJPA.model.StudentDTO;
import com.example.dataJPA.repository.*;
import com.example.dataJPA.service.StudentService;
import com.github.javafaker.Faker;
import org.hibernate.Hibernate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@SpringBootApplication
public class SpringDataJPARunner {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJPARunner.class,args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentService studentService, StudentRepository studentRepository, StudentIdCardRepository studentIdCardRepository, BookRepository bookRepository,EnrollmentRepository enrollmentRepository,CourseRepository courseRepository){
        return args -> {
            //randomGenerateStudents(studentService);
            //sorting(studentRepository);
            //pagination(studentRepository);
           // addStudentAndBooks(studentRepository, bookRepository,enrollmentRepository,courseRepository);

            int age = 10;
            Function<Integer,Integer> function = (s) -> age /2;
            System.out.println(function.apply(age));

            //studentIdCardRepository.save(new StudentIDCard(null,"12341",null));

//            studentRepository.deleteById(1L);
//            studentIdCardRepository.deleteById(1L);



//            StudentDTO student = StudentDTO.builder()
//                    .firstName("mira")
//                    .lastName("none")
//                    .email("mira.none@gmail.com")
//                    .age(21).build();
//            studentService.addStudent(student);
//
//            Student student2 = Student.builder()
//                    .firstName("ali")
//                    .lastName("ahmed")
//                    .email("ali.ahmed@gmail.com")
//                    .age(20).build();
//            studentRepository.save(student2);
//
//            System.out.println(studentRepository.count());
//
//            Consumer<Student> studentConsumer = stud -> {
//                System.out.println(stud.getFirstName() + " is Present");
//            };
//            studentRepository.findById(2L).ifPresent(studentConsumer);
//
//            studentRepository.findById(4L).ifPresent(studentConsumer);
//
//            studentRepository.findStudentByEmail("ali.ahmed@gmail.com")
//                    .ifPresent(studentConsumer);
//
//            studentRepository.findStudentByFirstNameAndAgeGreaterThanEqual("ali",19)
//                    .ifPresent(studentConsumer);
        };
    }

    public void addStudentAndBooks(StudentRepository studentRepository, BookRepository bookRepository, EnrollmentRepository enrollmentRepository, CourseRepository courseRepository) {


        //Student studentSaved = studentRepository.save(student);


//        studentSaved.addEnrollment(Enrollment.builder()
//                .enrollmentId(new EnrollmentId(studentSaved.getId(),courses.get(0).getId()))
//                        .course(courses.get(0)).student(student)
//                .build());

        //Course course3 = courseRepository.findById(1L).orElseThrow(() -> new IllegalArgumentException("Course not found"));
        //Enrollment enrollment = new Enrollment(student,course3);
        //student.addEnrollment(enrollment);



        System.out.println("Nicee");
        //Student savedStudent = studentRepository.save(student);

//        studentRepository.findById(1L).ifPresent(student1 -> {
//            student1.addBook(Book.builder()
//                    .bookName("chemistry").build());
//            studentRepository.save(student1);
//            System.out.println("Test");
//        });



    }

    private static void pagination(StudentRepository studentRepository) {
        PageRequest pageRequest = PageRequest.of(
                0,
                5,
                Sort.by("firstName").ascending());

        Page<Student> students= studentRepository.findAll(pageRequest);
        students.stream().collect(Collectors.toList()).forEach(System.out::println);
    }

    private static void sorting(StudentRepository studentRepository) {
        //This firstName should be of entity, not the sql one
        Sort sort = Sort.by(Sort.Direction.ASC,"firstName")
                .and(Sort.by("age").descending());
        studentRepository.findAll(sort).forEach(student -> System.out.printf("%s,%s%n",student.getFirstName(),student.getAge()));
    }

    private static void randomGenerateStudents(StudentService studentService) {
        Faker faker = new Faker();
        for(int i=0;i<20;i++){
            StudentDTO studentDTO = new StudentDTO();

            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();

            studentDTO.setFirstName(firstName);
            studentDTO.setLastName(lastName);
            studentDTO.setEmail(String.format("%s.%s@gmail.com",firstName,lastName));
            studentDTO.setAge(faker.number().numberBetween(17,55));

            studentService.addStudent(studentDTO);
        }
    }
}
