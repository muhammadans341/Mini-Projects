package org.example.customer.model.requests;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class Customer {
    @NotBlank(message = "firstName should not be empty")
    private String firstName;

    @NotBlank(message = "lastName should not be empty")
    private String lastName;

    @NotBlank(message = "email should not be empty")
    @Email(message = "not a valid email")
    private String email;
}
