package com.example.oneToOne.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "address")
@Getter
@Setter
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String address;

    @JsonManagedReference
    @OneToOne(mappedBy = "address") //Comment these two lines to make it one direction.
    private User user;
    //... getters and setters
}
