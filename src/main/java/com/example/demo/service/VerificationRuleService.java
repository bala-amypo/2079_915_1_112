package com.example.demo.service;

import com.example.demo.entity.VerificationRule;
import com.example.demo.entity.CredentialRecord;

public interface VerificationRuleService {

    VerificationRule createRule(VerificationRule rule);

    boolean validateCredential(CredentialRecord credential);
}
