package com.example.dataJPA.entity;

import lombok.*;

import javax.persistence.*;

@Entity(name = "StudentIDCard")
@Table(name = "student_id_card")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class StudentIDCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cardNumber;

    @OneToOne(
            cascade = {CascadeType.PERSIST ,CascadeType.REMOVE,CascadeType.MERGE},
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "student_id",
            referencedColumnName = "id",
            nullable = false
    )
    private Student student;
}
