package com.example.ecommerceapplication.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "user_info")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private String address;
    private String about;
    private String gender;
    private String phone;
    @Column(name = "created_at")
    private Date date;
    private boolean active;
}
