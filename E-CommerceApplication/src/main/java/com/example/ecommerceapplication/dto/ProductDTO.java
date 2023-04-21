package com.example.ecommerceapplication.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Data
public class ProductDTO {
    private Long id;
    @NotEmpty(message = "name is mandatory(should be non empty)")
    private String name;
    @NotNull
    @PositiveOrZero
    private BigDecimal price;
    @NotNull
    @PositiveOrZero
    private Long categoryId;
    @NotNull
    @PositiveOrZero
    private BigDecimal quantity;
    @NotNull
    private boolean stock;
    @NotNull
    private boolean live;
    private String description;
    private String imageName;
}
