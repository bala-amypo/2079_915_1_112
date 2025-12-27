package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "verification_request")
public class VerificationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔗 Relation to CredentialRecord
    @ManyToOne
    @JoinColumn(name = "credential_id")
    private CredentialRecord credentialRecord;

    private String status;

    private LocalDateTime requestedAt;

    public VerificationRequest() {}

    public Long getId() {
        return id;
    }

    public CredentialRecord getCredentialRecord() {
        return credentialRecord;
    }

    public void setCredentialRecord(CredentialRecord credentialRecord) {
        this.credentialRecord = credentialRecord;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getRequestedAt() {
        return requestedAt;
    }

    public void setRequestedAt(LocalDateTime requestedAt) {
        this.requestedAt = requestedAt;
    }
}
