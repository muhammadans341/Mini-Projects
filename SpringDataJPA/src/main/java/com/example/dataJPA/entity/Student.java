package com.example.dataJPA.entity;


import lombok.*;
import org.springframework.context.annotation.Configuration;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(
        name = "Student"
//        uniqueConstraints = {
//                @UniqueConstraint(
//                        name = "student_email_unique",
//                        columnNames = "email"
//                )
//        }
)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "first_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String firstName;

    @Column(
            name = "last_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String lastName;

    @Column(
            name = "email",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String email;

    @Column(
            name = "age"
    )
    private Integer age;

    @OneToOne(
            mappedBy = "student",
            cascade = {CascadeType.PERSIST , CascadeType.REMOVE,CascadeType.MERGE}
    )
    private StudentIDCard studentIDCard;

    @OneToMany(
            mappedBy = "student",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE,CascadeType.MERGE},
            fetch = FetchType.EAGER


    )
    private List<Book> books = new ArrayList<>();

    @OneToMany(
            mappedBy = "student",
            cascade = CascadeType.PERSIST
    )
    private Set<Enrollment> enrollments = new HashSet<>();

    public void addBook(Book book){
        if(!this.books.contains(book)){
            this.books.add(book);
            book.setStudent(this);
        }
    }

    public void removeBook(Book book){
        if(this.books.contains(book)){
            this.books.remove(book);
            book.setStudent(null);
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", studentIDCard=" + studentIDCard.getCardNumber() +
                '}';
    }

    public void addEnrollment(Enrollment enrollment){
        this.enrollments.add(enrollment);
    }

//    public void addToCourse(Course course){
//        Enrollment enrollment = new Enrollment(this,course);
//        enrollments.add(enrollment);
//        course.getEnrollments().add(enrollment);
//    }

    public void removeEnrollment(Enrollment enrollment){
        if(this.enrollments.contains(enrollment)){
            this.enrollments.remove(enrollment);
        }
    }
}
