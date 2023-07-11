package com.example.manyToMany.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "subject")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="SUBJECT_ID")
    private int subjectId;

    @Column(name="SUBJECT_DESC")
    private String subjectDesc;

    @Column(name="SUBJECT_NAME")
    private String subjectName;// if Field name and Column name is same no need to map with annotation

}
