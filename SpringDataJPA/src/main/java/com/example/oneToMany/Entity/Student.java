package com.example.oneToMany.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "student")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Student  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="CONTACT_NO")
    private String contactNo;

    private String fname;// if Field name and Column name is same no need to map with annotation

    private String lname;// default mapping is field name

    @ManyToOne(cascade=CascadeType.ALL,  fetch = FetchType.EAGER )
    @JoinColumn(name="BRANCH_ID", nullable=false)
    private Branch branch;
    // ... getters and setters
}
