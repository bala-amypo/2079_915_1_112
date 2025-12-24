package com.example.demo.service.impl;

import com.example.demo.entity.CredentialRecord;
import com.example.demo.service.VerificationRuleService;
import org.springframework.stereotype.Service;

@Service
public class VerificationRuleServiceImpl implements VerificationRuleService {

    /**
     * REQUIRED by interface
     */
    @Override
    public boolean validateCredential(CredentialRecord credential) {
        // Simple validation for now
        return credential != null && credential.getExpiryDate() != null;
    }
}
