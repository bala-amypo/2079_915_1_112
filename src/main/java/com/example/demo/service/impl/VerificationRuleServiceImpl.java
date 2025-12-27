package com.example.demo.service.impl;

import com.example.demo.entity.VerificationRule;
import com.example.demo.repository.VerificationRuleRepository;
import com.example.demo.service.VerificationRuleService;
import org.springframework.stereotype.Service;

@Service
public class VerificationRuleServiceImpl
        implements VerificationRuleService {

    private final VerificationRuleRepository repo;

    public VerificationRuleServiceImpl(VerificationRuleRepository repo) {
        this.repo = repo;
    }

    @Override
    public VerificationRule createRule(VerificationRule rule) {
        return repo.save(rule);
    }
}
