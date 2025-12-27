package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class VerificationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleCode;
    private Boolean active = true;

    public Long getId() { return id; }

    public String getRuleCode() { return ruleCode; }
    public void setRuleCode(String ruleCode) { this.ruleCode = ruleCode; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
