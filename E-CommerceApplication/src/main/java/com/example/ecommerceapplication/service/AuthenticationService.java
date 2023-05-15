package com.example.ecommerceapplication.service;

import com.example.ecommerceapplication.model.User;
import com.example.ecommerceapplication.model.request.AuthenticationRequest;
import com.example.ecommerceapplication.model.response.AuthenticationResponse;
import com.example.ecommerceapplication.repository.UserRepository;
import com.example.ecommerceapplication.utility.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationProvider authenticationProvider;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
        );
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(()-> new UsernameNotFoundException("User not found"));
        return  AuthenticationResponse.builder()
                .token(jwtUtil.generateToken(user))
                .build();
    }
}
