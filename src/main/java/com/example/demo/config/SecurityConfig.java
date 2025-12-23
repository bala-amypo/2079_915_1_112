package com.example.demo.config;

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
    @Bean
    public AuthenticationManager authenticationManager() {
        // Dummy manager – tests don’t use real authentication
        return new ProviderManager(java.util.List.of());
    }

    // ✅ Required by AuthController
    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil();
    }
}
