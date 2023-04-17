package com.example.ecommerceapplication.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    private Long id;
    private String title;
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private List<Product> products;
}
