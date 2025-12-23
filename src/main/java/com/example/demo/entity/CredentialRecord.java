package com.example.demo.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "credential_record")
public class CredentialRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ✅ REQUIRED BY TESTS
    private String credentialCode;

    private String title;
    private String issuer;
    private String status;

    private LocalDate expiryDate;

    @ManyToMany
    @JoinTable(
        name = "credential_rules",
        joinColumns = @JoinColumn(name = "credential_id"),
        inverseJoinColumns = @JoinColumn(name = "rule_id")
    )
    private Set<VerificationRule> rules = new HashSet<>();

    public CredentialRecord() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    // ✅ REQUIRED METHOD
    public String getCredentialCode() {
        return credentialCode;
    }

    public void setCredentialCode(String credentialCode) {
        this.credentialCode = credentialCode;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getIssuer() { return issuer; }
    public void setIssuer(String issuer) { this.issuer = issuer; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }

    public Set<VerificationRule> getRules() {
        return rules;
    }

    public void setRules(Set<VerificationRule> rules) {
        this.rules = rules;
    }
}
