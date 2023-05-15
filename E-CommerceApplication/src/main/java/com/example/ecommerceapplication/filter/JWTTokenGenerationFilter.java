package com.example.ecommerceapplication.filter;

import com.example.ecommerceapplication.service.CustomUserDetailsService;
import com.example.ecommerceapplication.utility.JwtUtil;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTTokenGenerationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private CustomUserDetailsService UserDetailsService;

    @Override
    protected void doFilterInternal(
             HttpServletRequest request,
             @NotNull HttpServletResponse response,
             @NotNull FilterChain filterChain)
            throws ServletException, IOException
    {
        final String authHeader = request.getHeader("AUTHORIZATION");

        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }
        final String jwt = authHeader.substring(7);
        String userEmail = jwtUtil.extractUserEmail(jwt);
        if(userEmail!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails = this.UserDetailsService.loadUserByUsername(userEmail);
            if(userDetails!=null && jwtUtil.isTokenValid(jwt,userDetails)){
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
