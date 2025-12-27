package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class VerificationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ✅ store only credentialId (NO relationship confusion)
    private Long credentialId;

    private String status;

    public VerificationRequest() {
    }

    // getters & setters
    public Long getId() {
        return id;
    }

    public Long getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Long credentialId) {
        this.credentialId = credentialId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
