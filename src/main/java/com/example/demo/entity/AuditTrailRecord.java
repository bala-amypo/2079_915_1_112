package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "audit_trail_record")
public class AuditTrailRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * IMPORTANT:
     * Tests + Repository expect THIS FIELD NAME exactly
     */
    @Column(nullable = false)
    private Long credentialId;

    @Column(nullable = false)
    private String action;

    @Column(nullable = false)
    private String performedBy;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    // ---------------- CONSTRUCTORS ----------------

    public AuditTrailRecord() {
        // Required by JPA
    }

    public AuditTrailRecord(Long credentialId, String action, String performedBy) {
        this.credentialId = credentialId;
        this.action = action;
        this.performedBy = performedBy;
        this.timestamp = LocalDateTime.now();
    }

    // ---------------- GETTERS & SETTERS ----------------

    public Long getId() {
        return id;
    }

    public Long getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Long credentialId) {
        this.credentialId = credentialId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getPerformedBy() {
        return performedBy;
    }

    public void setPerformedBy(String performedBy) {
        this.performedBy = performedBy;
    }

    /*
     * 🔴 REQUIRED BY:
     * VerificationRequestServiceImpl
     * AuditTrailServiceImpl
     * Test cases
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
