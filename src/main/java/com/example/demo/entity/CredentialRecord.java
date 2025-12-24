package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "credential_record")
public class CredentialRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Unique credential identifier
    @Column(nullable = false, unique = true)
    private String credentialCode;

    // Issuer of the credential (e.g., University, Govt)
    @Column(nullable = false)
    private String issuer;

    // ✅ REQUIRED BY TEST CASES (NOT credentialName)
    @Column(nullable = false)
    private String type;

    // Credential holder reference
    @Column(nullable = false)
    private Long holderId;

    // Expiry date of credential
    @Column(nullable = false)
    private LocalDate expiryDate;

    // Status: ACTIVE / EXPIRED / REVOKED
    @Column(nullable = false)
    private String status;

    // Extra metadata stored as JSON string
    @Column(columnDefinition = "TEXT")
    private String metadataJson;

    /* =========================
       Constructors
       ========================= */

    public CredentialRecord() {
    }

    public CredentialRecord(String credentialCode,
                            String issuer,
                            String type,
                            Long holderId,
                            LocalDate expiryDate,
                            String status,
                            String metadataJson) {
        this.credentialCode = credentialCode;
        this.issuer = issuer;
        this.type = type;
        this.holderId = holderId;
        this.expiryDate = expiryDate;
        this.status = status;
        this.metadataJson = metadataJson;
    }

    /* =========================
       Getters and Setters
       ========================= */

    public Long getId() {
        return id;
    }

    public String getCredentialCode() {
        return credentialCode;
    }

    public void setCredentialCode(String credentialCode) {
        this.credentialCode = credentialCode;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getHolderId() {
        return holderId;
    }

    public void setHolderId(Long holderId) {
        this.holderId = holderId;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMetadataJson() {
        return metadataJson;
    }

    public void setMetadataJson(String metadataJson) {
        this.metadataJson = metadataJson;
    }
}
