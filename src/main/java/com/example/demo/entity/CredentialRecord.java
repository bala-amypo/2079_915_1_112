package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "credential_record")
public class CredentialRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Credential code is required")
    @Column(unique = true)
    private String credentialCode;

    @NotBlank(message = "Credential name is required")
    private String credentialName;

    @NotNull(message = "Expiry date is required")
    @Future(message = "Expiry date must be in future")
    private LocalDate expiryDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull(message = "User is required")
    private User user;
