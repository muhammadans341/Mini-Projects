package com.springsecurity.practice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static com.springsecurity.practice.config.ApplicationUserPermissions.TEACHER_WRITE;
import static com.springsecurity.practice.config.ApplicationUserRoles.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig {

    @Bean
    public SecurityFilterChain defaultSecurityFiler(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/api/v1/students/*").hasRole(TEACHER.name())
//                .antMatchers(HttpMethod.POST,"/management/**").hasAuthority(TEACHER_WRITE.getPermission())
//                .antMatchers(HttpMethod.DELETE,"/management/**").hasAuthority(TEACHER_WRITE.getPermission())
//                .antMatchers(HttpMethod.PUT,"/management/**").hasAuthority(TEACHER_WRITE.getPermission())
//                .antMatchers(HttpMethod.GET,"/management/**").hasAnyRole(TEACHER.name(),ASSISTANT_TEACHER.name())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();
        return httpSecurity.build();
    }

    @Bean
    protected UserDetailsService userDetailsService(){
        UserDetails yuji = User.builder()
                .username("yuji")
                .password(passwordEncoder().encode("sukuna"))
                //.roles(ASSISTANT_TEACHER.name())
                .authorities(ASSISTANT_TEACHER.getGrantedAuthorities())
                .build();

        UserDetails gojo = User.builder()
                .username("gojo")
                .password(passwordEncoder().encode("sukuna"))
                //.roles(TEACHER.name())
                .authorities(TEACHER.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(yuji,gojo);
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
