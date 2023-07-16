package com.example.dataJPA.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="course")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "name",
            columnDefinition = "TEXT"
    )
    private String name;
    @Column(
            name = "department",
            columnDefinition = "TEXT"
    )
    private String department;

    @OneToMany(
            mappedBy = "course",
            cascade = CascadeType.ALL
    )
    private Set<Enrollment> enrollments = new HashSet<>();
}
