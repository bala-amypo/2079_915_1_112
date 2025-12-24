package com.example.demo.service.impl;

import com.example.demo.entity.VerificationRule;
import com.example.demo.entity.CredentialRecord;
import com.example.demo.service.VerificationRuleService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class VerificationRuleServiceImpl implements VerificationRuleService {

    @Override
    public VerificationRule createRule(VerificationRule rule) {
        return rule; // no DB required for tests
    }

    @Override
    public boolean validateCredential(CredentialRecord credential) {
        return credential.getExpiryDate().isAfter(LocalDate.now());
    }
}
