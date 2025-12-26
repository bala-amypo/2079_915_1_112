package com.example.demo.service.impl;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.VerificationRequestService;

public class VerificationRequestServiceImpl
        implements VerificationRequestService {

    private final VerificationRequestRepository requestRepo;
    private final CredentialRecordRepository credentialRepo;
    private final VerificationRuleRepository ruleRepo;
    private final AuditTrailRecordRepository auditRepo;

    public VerificationRequestServiceImpl(
            VerificationRequestRepository requestRepo,
            CredentialRecordServiceImpl credentialService,
            VerificationRuleServiceImpl ruleService,
            AuditTrailServiceImpl auditService) {

        this.requestRepo = requestRepo;
        this.credentialRepo = credentialService.repository;
        this.ruleRepo = ruleService.repository;
        this.auditRepo = auditService.repository;
    }

    @Override
    public VerificationRequest initiateVerification(VerificationRequest request) {
        return requestRepo.save(request);
    }

    @Override
    public VerificationRequest processVerification(Long requestId) {
        VerificationRequest request =
                requestRepo.findById(requestId).orElseThrow();

        CredentialRecord credential = credentialRepo.findAll().stream()
                .filter(c -> c.getId().equals(request.getCredentialId()))
                .findFirst().orElse(null);

        boolean expired = credential != null &&
                credential.getExpiryDate() != null &&
                credential.getExpiryDate().isBefore(LocalDate.now());

        request.setStatus(expired ? "FAILED" : "SUCCESS");

        AuditTrailRecord audit = new AuditTrailRecord();
        audit.setCredentialId(request.getCredentialId());
        auditRepo.save(audit);

        return requestRepo.save(request);
    }

    @Override
    public List<VerificationRequest> getRequestsByCredential(Long credentialId) {
        return requestRepo.findByCredentialId(credentialId);
    }
}
