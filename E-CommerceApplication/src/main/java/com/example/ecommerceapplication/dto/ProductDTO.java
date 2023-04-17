package com.example.ecommerceapplication.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private BigDecimal price;
    private Long categoryId;
    private BigDecimal quantity;
    private boolean stock;
    private boolean live;
    private String description;
    private String imageName;
}
