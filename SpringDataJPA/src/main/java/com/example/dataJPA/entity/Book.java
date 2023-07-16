package com.example.dataJPA.entity;

import lombok.*;
import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;

@Entity
@Table(name = "Book")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_name")
    private String bookName;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "student_id",
            referencedColumnName = "id"
    )
    private Student student;
}
