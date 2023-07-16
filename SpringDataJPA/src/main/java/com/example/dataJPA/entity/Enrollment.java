package com.example.dataJPA.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.checkerframework.checker.lock.qual.NewObject;

import javax.persistence.*;
import java.util.Objects;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="Enrollment")
public class Enrollment {
    @EmbeddedId
    private EnrollmentId enrollmentId = new EnrollmentId();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("studentId")
    @JoinColumn(name="student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("courseId")
    @JoinColumn(name="course_id")
    private Course course;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enrollment that = (Enrollment) o;
        return Objects.equals(student.getStudentIDCard().getCardNumber(), that.student.getStudentIDCard().getCardNumber()) && Objects.equals(course.getName(), that.course.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(student.getStudentIDCard().getCardNumber(), course.getName());
    }

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.enrollmentId = new EnrollmentId(student.getId(),course.getId());
    }
}
