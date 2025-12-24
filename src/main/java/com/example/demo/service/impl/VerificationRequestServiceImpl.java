package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VerificationRequestServiceImpl implements VerificationRequestService {

    private final VerificationRequestRepository requestRepo;
    private final CredentialRecordRepository credentialRepo;
    private final AuditTrailService auditService;
    private final VerificationRuleService ruleService;

    public VerificationRequestServiceImpl(
            VerificationRequestRepository requestRepo,
            CredentialRecordRepository credentialRepo,
            AuditTrailService auditService,
            VerificationRuleService ruleService) {
        this.requestRepo = requestRepo;
        this.credentialRepo = credentialRepo;
        this.auditService = auditService;
        this.ruleService = ruleService;
    }

    @Override
    public VerificationRequest initiateVerification(VerificationRequest request) {
        request.setStatus("PENDING");
        request.setRequestedAt(LocalDateTime.now());
        return requestRepo.save(request);
    }

    @Override
    public VerificationRequest processVerification(Long requestId) {
        VerificationRequest request = requestRepo.findById(requestId).orElseThrow();
        CredentialRecord credential = credentialRepo.findById(request.getCredentialId()).orElseThrow();

        boolean valid = ruleService.validateCredential(credential);
        request.setStatus(valid ? "APPROVED" : "REJECTED");

        AuditTrailRecord audit = new AuditTrailRecord();
        audit.setCredentialId(credential.getId());
        audit.setAction(request.getStatus());
        audit.setTimestamp(LocalDateTime.now());
        auditService.logEvent(audit);

        return requestRepo.save(request);
    }

    @Override
    public List<VerificationRequest> getRequestsByCredential(Long credentialId) {
        return requestRepo.findByCredentialId(credentialId);
    }
}
