package com.example.oneToMany.Entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "branch")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="BRANCH_ID")
    private Long branchId;

    @Column(name="BRANCH_NAME")
    private String branchName;

    @Column(name="BRANCH_SHORT_NAME")
    private String branchShortName;

    private String description;

    //uni-directional one-to-many association to Student
    @OneToMany(mappedBy = "branch",  cascade={CascadeType.ALL}, fetch = FetchType.EAGER)
    private Set<StudentTest> studentTests; // Each branch have many Students (list of)
}
