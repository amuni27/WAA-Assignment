package com.waa.assignment.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import org.springframework.security.core.Authentication;

import static com.waa.assignment.domain.UserType.ADMIN;

public class JwtFilter extends OncePerRequestFilter {

    private static final String[] WHITE_LIST_URL = {
            "/api/v1/comment",
            "/api/v1/comment/{id}",
            "/api/v1/post",
            "/api/v1/post/{id}",
            "/api/v1/user",
            "/api/v1/user/{id}",
            "/api/v1/user/{id}/posts",
    };

    @Autowired
    JwtTokenService jwtTokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = request.getHeader("Authorization");
            if (token != null) {
                String subToken = token.substring(7);
                Authentication authentication = jwtTokenService.getAuthentication(subToken);
                if (authentication != null) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    response.setContentType("application/json");
                    response.getWriter().write("{\"error\": \"JWT token validation failed\"}");
                    return;
                }
            }
        } catch (Exception e) {
//            logger.error("JWT token validation failed", e);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"JWT token validation failed\"}");
            return;
        }
        filterChain.doFilter(request, response);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers(WHITE_LIST_URL).permitAll()
                        .requestMatchers("/admin").hasAnyRole(ADMIN.name())
                        .anyRequest().authenticated())
                .addFilterBefore(this, UsernamePasswordAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
