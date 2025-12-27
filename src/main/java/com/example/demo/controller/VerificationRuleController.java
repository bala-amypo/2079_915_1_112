package com.example.demo.controller;

import com.example.demo.entity.VerificationRule;
import com.example.demo.service.VerificationRuleService;
import org.springframework.http.ResponseEntity;

public class VerificationRuleController {

    private final VerificationRuleService service;

    public VerificationRuleController(VerificationRuleService service) {
        this.service = service;
    }

    public ResponseEntity<VerificationRule> create(VerificationRule rule) {
        return ResponseEntity.ok(service.createRule(rule));
    }
}
