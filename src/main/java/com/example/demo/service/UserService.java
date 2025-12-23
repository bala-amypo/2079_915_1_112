package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.User;

public interface UserService {

    // Register a new user
    User registerUser(User user);

    // Login user
    User loginUser(String email, String password);

    // Find user by email
    User findByEmail(String email);

    // Get user by ID
    User getUserById(Long id);

    // Get all users
    List<User> getAllUsers();
}
