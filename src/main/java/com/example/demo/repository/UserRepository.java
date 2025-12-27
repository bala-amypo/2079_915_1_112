package com.example.demo.repository;

import com.example.demo.entity.User;
import java.util.Optional;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository {

    boolean existsByEmail(String email);

    User save(User user);

    Optional<User> findByEmail(String email);
}
