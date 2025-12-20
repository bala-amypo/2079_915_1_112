package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // ================= REGISTER =================
    @PostMapping("/register")
    public ResponseEntity<User> register(
            @Validated(User.OnRegister.class)
            @RequestBody User user) {

        User savedUser = userService.registerUser(user);
        return ResponseEntity.ok(savedUser);
    }

    // ================= LOGIN =================
    @PostMapping("/login")
    public ResponseEntity<String> login(
            @Validated(User.OnLogin.class)
            @RequestBody User requestUser) {

        User dbUser = userService.findByEmail(requestUser.getEmail());

        if (!dbUser.getPassword().equals(requestUser.getPassword())) {
            throw new ResourceNotFoundException("Invalid email or password");
        }

        return ResponseEntity.ok("Login successful");
    }
}
