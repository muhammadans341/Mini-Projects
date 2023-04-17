package com.example.ecommerceapplication.model;

import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;
@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Long id;
    @Column(name = "product_name")
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="category_id")
    private Category category;
    private BigDecimal price;
    private BigDecimal quantity;
    private boolean stock;
    private boolean live;
    private String description;
    private String imageName;
}
