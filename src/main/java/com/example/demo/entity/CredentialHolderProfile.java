package com.example.demo.entity;

public class CredentialHolderProfile {
    private Long id;
    private String email;
    private String organization;
    private Boolean active = true;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getOrganization() { return organization; }
    public void setOrganization(String organization) { this.organization = organization; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
