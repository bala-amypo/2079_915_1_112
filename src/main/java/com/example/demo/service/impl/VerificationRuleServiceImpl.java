package com.example.demo.service.impl;

import com.example.demo.entity.VerificationRule;
import com.example.demo.repository.VerificationRuleRepository;
import com.example.demo.service.VerificationRuleService;
@Service
public class VerificationRuleServiceImpl
        implements VerificationRuleService {

    private final VerificationRuleRepository repository;

    public VerificationRuleServiceImpl(
            VerificationRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public VerificationRule createRule(VerificationRule rule) {
        return repository.save(rule);
    }
}
