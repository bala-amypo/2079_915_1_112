package com.example.demo.service;

import com.example.demo.entity.CredentialRecord;

public interface VerificationRuleService {

    boolean validateCredential(CredentialRecord credential);

}
