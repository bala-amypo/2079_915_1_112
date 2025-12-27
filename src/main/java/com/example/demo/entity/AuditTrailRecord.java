package com.example.demo.entity;

import java.time.LocalDateTime;

public class AuditTrailRecord {
    private Long id;
    private Long credentialId;
    private LocalDateTime loggedAt;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCredentialId() { return credentialId; }
    public void setCredentialId(Long credentialId) { this.credentialId = credentialId; }

    public LocalDateTime getLoggedAt() { return loggedAt; }
    public void setLoggedAt(LocalDateTime loggedAt) { this.loggedAt = loggedAt; }
}
