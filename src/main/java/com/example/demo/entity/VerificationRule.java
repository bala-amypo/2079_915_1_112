package com.example.demo.entity;

public class VerificationRule {
    private Long id;
    private String ruleCode;
    private Boolean active = true;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRuleCode() { return ruleCode; }
    public void setRuleCode(String ruleCode) { this.ruleCode = ruleCode; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
