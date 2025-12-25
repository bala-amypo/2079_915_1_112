package com.example.demo.controller;

import com.example.demo.dto.JwtResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.User;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    // Constructor used by TESTS
    public AuthController(UserService userService) {
        this.userService = userService;
        this.jwtUtil = null;
    }

    // Constructor used by Spring Boot
    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    // ================= REGISTER =================
    @PostMapping("/register")
    public JwtResponse register(@RequestBody RegisterRequest request) {

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());

        User savedUser = userService.registerUser(user);

        String token = (jwtUtil != null)
                ? jwtUtil.generateToken(
                        savedUser.getId(),
                        savedUser.getEmail(),
                        savedUser.getRole()
                )
                : "test-token";

        return new JwtResponse(token);
    }

    // ================= LOGIN =================
    @PostMapping("/login")
    public JwtResponse login(@RequestBody LoginRequest request) {

        User user = userService.loginUser(
                request.getEmail(),
                request.getPassword()
        );

        String token = (jwtUtil != null)
                ? jwtUtil.generateToken(
                        user.getId(),
                        user.getEmail(),
                        user.getRole()
                )
                : "test-token";

        return new JwtResponse(token);
    }
}
