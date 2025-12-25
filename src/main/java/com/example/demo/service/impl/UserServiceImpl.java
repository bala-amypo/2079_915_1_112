package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    @Override
    public User registerUser(User user) {
        if (repo.findByEmail(user.getEmail()).isPresent()) {
            return null; // REQUIRED by test t50
        }
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    // ✅ FIXED METHOD
    @Override
    public User loginUser(String email, String password) {

        User user = repo.findByEmail(email).orElse(null);
        if (user == null) {
            return null; // REQUIRED by t56
        }

        if (!encoder.matches(password, user.getPassword())) {
            return null; // REQUIRED by t56
        }

        return user; // SUCCESS
    }

    @Override
    public User findByEmail(String email) {
        return repo.findByEmail(email).orElse(null);
    }

    @Override
    public User getUserById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return repo.findAll();
    }
}
