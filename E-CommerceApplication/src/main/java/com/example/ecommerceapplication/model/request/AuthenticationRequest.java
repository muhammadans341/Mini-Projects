package com.example.ecommerceapplication.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AuthenticationRequest {
    private String email;
    private String password;
}
