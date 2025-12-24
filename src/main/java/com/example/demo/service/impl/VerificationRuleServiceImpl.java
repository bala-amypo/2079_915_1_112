package com.example.demo.service.impl;

import com.example.demo.entity.CredentialRecord;
import com.example.demo.entity.VerificationRule;
import com.example.demo.repository.VerificationRuleRepository;
import com.example.demo.service.VerificationRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VerificationRuleServiceImpl implements VerificationRuleService {

    private final VerificationRuleRepository repository;

    public VerificationRuleServiceImpl(VerificationRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public VerificationRule save(VerificationRule rule) {
        return repository.save(rule);
    }

    @Override
    public boolean validateCredential(CredentialRecord credential) {
        return credential != null;
    }

    @Override
    public List<VerificationRule> getAllRules() {
        return repository.findAll();
    }
}
