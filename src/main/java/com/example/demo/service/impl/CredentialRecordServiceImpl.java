package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.entity.CredentialRecord;
import com.example.demo.entity.User;
import com.example.demo.entity.VerificationRule;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CredentialRecordRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VerificationRuleRepository;
import com.example.demo.service.CredentialRecordService;

@Service
public class CredentialRecordServiceImpl
        implements CredentialRecordService {

    private final CredentialRecordRepository credentialRepo;
    private final UserRepository userRepo;
    private final VerificationRuleRepository ruleRepo;

    public CredentialRecordServiceImpl(
            CredentialRecordRepository credentialRepo,
            UserRepository userRepo,
            VerificationRuleRepository ruleRepo) {
        this.credentialRepo = credentialRepo;
        this.userRepo = userRepo;
        this.ruleRepo = ruleRepo;
    }

    @Override
    public CredentialRecord createCredential(CredentialRecord record) {

        User user = userRepo.findById(record.getUser().getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        VerificationRule rule = ruleRepo
                .findById(record.getVerificationRule().getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Rule not found"));

        record.setUser(user);
        record.setVerificationRule(rule);

        return credentialRepo.save(record);
    }
}
