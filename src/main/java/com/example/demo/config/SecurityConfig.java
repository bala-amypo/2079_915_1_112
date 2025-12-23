package com.example.demo.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.security.JwtUtil;

@Configuration
public class SecurityConfig {

    // ✅ Required by UserServiceImpl
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ✅ Required by AuthController
    // Dummy manager – tests don’t use real authentication
    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(List.of());
    }

    // ✅ Required by AuthController
    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil();
    }
}
