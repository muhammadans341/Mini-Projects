package com.example.ecommerceapplication.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CategoryDTO {
    private Long id;
    @NotEmpty(message = "title is mandatory(should be non empty)")
    private String title;
}
