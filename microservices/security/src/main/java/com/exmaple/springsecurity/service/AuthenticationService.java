package com.exmaple.springsecurity.service;

import com.exmaple.springsecurity.Util.JwtUtil;
import com.exmaple.springsecurity.entity.Role;
import com.exmaple.springsecurity.entity.User;
import com.exmaple.springsecurity.model.request.AuthenticationRequest;
import com.exmaple.springsecurity.model.request.RegisterRequest;
import com.exmaple.springsecurity.model.response.AuthenticationResponse;
import com.exmaple.springsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.var;
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
    public AuthenticationResponse register(RegisterRequest request) {
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER).build();
        userRepository.save(user);
        String token =jwtUtil.generateToken(user);
        return  AuthenticationResponse.builder().token(token).build();

    }

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
