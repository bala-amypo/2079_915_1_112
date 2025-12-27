package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class AuditTrailRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String action;

    private Long credentialId;

    // ✅ REQUIRED by JPA
    public AuditTrailRecord() {
    }

    // ✅ REQUIRED by service logic
    public AuditTrailRecord(String action, Long credentialId) {
        this.action = action;
        this.credentialId = credentialId;
    }

    // getters & setters
    public Long getId() {
        return id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Long getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Long credentialId) {
        this.credentialId = credentialId;
    }
}
