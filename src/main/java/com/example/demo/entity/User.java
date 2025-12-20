package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    // 🔹 Validation groups (NO separate package needed)
    public interface OnRegister {}
    public interface OnLogin {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(
        message = "Name cannot be empty",
        groups = OnRegister.class
    )
    private String name;

    @Email(
        message = "Invalid email format",
        groups = {OnRegister.class, OnLogin.class}
    )
    @NotBlank(
        message = "Email cannot be empty",
        groups = {OnRegister.class, OnLogin.class}
    )
    @Column(unique = true)
    private String email;

    @NotBlank(
        message = "Password cannot be empty",
        groups = {OnRegister.class, OnLogin.class}
    )
    @Size(
        min = 6,
        message = "Password must be at least 6 characters",
        groups = OnRegister.class
    )
    private String password;

    // ===== Getters & Setters =====

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
}
