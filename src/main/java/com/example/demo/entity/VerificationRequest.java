package com.example.demo.entity;

public class VerificationRequest {
    private Long id;
    private Long credentialId;
    private String status;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCredentialId() { return credentialId; }
    public void setCredentialId(Long credentialId) { this.credentialId = credentialId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
