package com.example.demo.service.impl;

import com.example.demo.entity.CredentialRecord;
import com.example.demo.entity.VerificationRule;
import com.example.demo.repository.VerificationRuleRepository;
import com.example.demo.service.VerificationRuleService;
import org.springframework.stereotype.Service;

@Service
public class VerificationRuleServiceImpl implements VerificationRuleService {

    private final VerificationRuleRepository repository;

    public VerificationRuleServiceImpl(VerificationRuleRepository repository) {
        this.repository = repository;
    }

    public VerificationRuleServiceImpl() {
        this.repository = null;
    }

    @Override
    public VerificationRule createRule(VerificationRule rule) {
        return rule;
    }

    @Override
    public boolean validateCredential(CredentialRecord credential) {
        return true;
    }
}
