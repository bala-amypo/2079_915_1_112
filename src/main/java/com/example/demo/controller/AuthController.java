package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // REGISTER
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {

        user.setRole("USER"); // default role
        User saved = userService.registerUser(user);

        return ResponseEntity.ok(saved);
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {

        User dbUser = userService.findByEmail(user.getEmail());

        if (!dbUser.getPassword().equals(user.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid credentials");
        }

        return ResponseEntity.ok("Login successful. Role: " + dbUser.getRole());
    }
}
