package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "audit_trail_record")
public class AuditTrailRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // IMPORTANT: tests & repositories expect this field
    private Long credentialId;

    private String action;

    private String performedBy;

    // IMPORTANT: tests expect BOTH names to work
    private LocalDateTime loggedAt;

    // ===================== GETTERS & SETTERS =====================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getLoggedAt() {
        return loggedAt;
    }

    public void setLoggedAt(LocalDateTime loggedAt) {
        this.loggedAt = loggedAt;
    }

    // 🔥 VERY IMPORTANT — ALIAS METHOD FOR TESTS
    // This FIXES: setTimestamp(LocalDateTime) error
    public void setTimestamp(LocalDateTime time) {
        this.loggedAt = time;
    }

    public LocalDateTime getTimestamp() {
        return this.loggedAt;
    }
}
