package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.*;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VerificationRequestServiceImpl implements VerificationRequestService {

    private final VerificationRequestRepository requestRepository;
    private final CredentialRecordRepository credentialRepository;
    private final VerificationRuleService ruleService;
    private final AuditTrailService auditTrailService;

    public VerificationRequestServiceImpl(
            VerificationRequestRepository requestRepository,
            CredentialRecordRepository credentialRepository,
            VerificationRuleService ruleService,
            AuditTrailService auditTrailService) {

        this.requestRepository = requestRepository;
        this.credentialRepository = credentialRepository;
        this.ruleService = ruleService;
        this.auditTrailService = auditTrailService;
    }

    @Override
    public VerificationRequest initiateVerification(VerificationRequest request) {

        request.setRequestedAt(LocalDateTime.now());
        request.setStatus("PENDING");

        CredentialRecord credential =
                credentialRepository.findById(request.getCredentialId())
                        .orElseThrow(() -> new RuntimeException("Credential not found"));

        boolean valid = ruleService.validateCredential(credential);

        request.setStatus(valid ? "VERIFIED" : "REJECTED");
        VerificationRequest savedRequest = requestRepository.save(request);

        AuditTrailRecord audit = new AuditTrailRecord();
        audit.setAction("VERIFY_CREDENTIAL");
        audit.setTimestamp(LocalDateTime.now());
        audit.setDetails("Verification " + request.getStatus());

        auditTrailService.save(audit);

        return savedRequest;
    }
}
