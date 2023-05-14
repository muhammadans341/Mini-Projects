package com.example.ecommerceapplication.dto;

import com.example.ecommerceapplication.enums.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.HashSet;

@Data
public class UserDTO {
    private Long Id;
    @NotEmpty(message = "name is mandatory(should be non empty)")
    private String name;
    @NotEmpty(message = "email is mandatory(should be non empty)")
    @Email(message = "email should be valid")
    private String email;
    @NotEmpty(message = "password is mandatory(should be non empty)")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @NotEmpty(message = "address is mandatory(should be non empty)")
    private String address;
    private String about;
    @NotNull(message = "gender is mandatory(should be non empty)")
    private Gender gender;
    @Size(message = "phone size should be between 9 to 11")
    @NotEmpty(message = "phone is mandatory(should be non empty)")
    private String phone;
    private Date date;
    private boolean active;
    private HashSet<String> roles;
}
