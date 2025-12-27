package com.example.demo.repository;

import java.util.*;
import com.example.demo.entity.User;

public interface UserRepository {
    boolean existsByEmail(String email);
    User save(User user);
    Optional<User> findByEmail(String email);
}
