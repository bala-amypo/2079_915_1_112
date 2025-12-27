package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
public class CredentialRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String credentialCode;
    private String title;
    private String issuer;
    private String credentialType;
    private String metadataJson;
    private LocalDate expiryDate;
    private String status;

    @ManyToMany
    private List<VerificationRule> rules = new ArrayList<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCredentialCode() { return credentialCode; }
    public void setCredentialCode(String credentialCode) { this.credentialCode = credentialCode; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getIssuer() { return issuer; }
    public void setIssuer(String issuer) { this.issuer = issuer; }

    public String getCredentialType() { return credentialType; }
    public void setCredentialType(String credentialType) { this.credentialType = credentialType; }

    public String getMetadataJson() { return metadataJson; }
    public void setMetadataJson(String metadataJson) { this.metadataJson = metadataJson; }

    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public List<VerificationRule> getRules() { return rules; }
    public void setRules(List<VerificationRule> rules) { this.rules = rules; }
}
