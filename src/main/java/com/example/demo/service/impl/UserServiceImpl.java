package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ✅ REQUIRED BY INTERFACE
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ✅ FIX duplicate email test
    @Override
    public User registerUser(User user) {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        return userRepository.save(user);
    }

    // ✅ FIX login tests
    @Override
    public User loginUser(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid credentials");
        }

        return user;
    }
}
