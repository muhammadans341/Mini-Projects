package com.example.manyToMany.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
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

    //uni-directional many-to-many association to Student
    @ManyToMany(cascade={CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(name = "BRANCH_SUBJECT", joinColumns =@JoinColumn(name = "BRANCH_ID"), inverseJoinColumns=@JoinColumn(name ="SUBJECT_ID"))
    private List<Subject> subjects; // Each branch have many Students (list of)
}
