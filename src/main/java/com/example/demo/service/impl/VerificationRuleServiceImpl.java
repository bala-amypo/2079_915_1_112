package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.VerificationRule;
import com.example.demo.repository.VerificationRuleRepository;
import com.example.demo.service.VerificationRuleService;

@Service
public class VerificationRuleServiceImpl implements VerificationRuleService {

    private final VerificationRuleRepository repository;

    /*
     * THIS CONSTRUCTOR IS REQUIRED BY TEST CASES
     * DigitalCredentialVerificationEngineTest uses:
     * new VerificationRuleServiceImpl(verificationRuleRepository)
     */
    public VerificationRuleServiceImpl(VerificationRuleRepository repository) {
        this.repository = repository;
    }

    /*
     * THIS NO-ARGS CONSTRUCTOR IS REQUIRED BY SPRING
     * (Spring can still inject via proxy if needed)
     */
    public VerificationRuleServiceImpl() {
        this.repository = null;
    }

    @Override
    public VerificationRule save(VerificationRule rule) {
        return repository.save(rule);
    }

    @Override
    public VerificationRule getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<VerificationRule> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
