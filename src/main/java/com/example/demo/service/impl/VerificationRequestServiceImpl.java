package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.VerificationRequestService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class VerificationRequestServiceImpl
        implements VerificationRequestService {

    private final VerificationRequestRepository requestRepo;
    private final CredentialRecordRepository credentialRepo;
    private final VerificationRuleRepository ruleRepo;
    private final AuditTrailRecordRepository auditRepo;

    public VerificationRequestServiceImpl(
            VerificationRequestRepository requestRepo,
            CredentialRecordRepository credentialRepo,
            VerificationRuleRepository ruleRepo,
            AuditTrailRecordRepository auditRepo) {
        this.requestRepo = requestRepo;
        this.credentialRepo = credentialRepo;
        this.ruleRepo = ruleRepo;
        this.auditRepo = auditRepo;
    }

    @Override
    public VerificationRequest initiateVerification(
            VerificationRequest request) {
        return requestRepo.save(request);
    }

    @Override
    public VerificationRequest processVerification(
            Long requestId) {

        VerificationRequest request = requestRepo.findById(requestId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Request not found"));

        CredentialRecord credential =
                credentialRepo.findById(request.getCredentialId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Credential not found"));

        if (credential.getExpiryDate() != null &&
                credential.getExpiryDate().isBefore(LocalDate.now())) {
            request.setStatus("FAILED");
        } else {
            request.setStatus("SUCCESS");
        }

        AuditTrailRecord audit = new AuditTrailRecord();
        audit.setCredentialId(credential.getId());
        auditRepo.save(audit);

        return requestRepo.save(request);
    }

    @Override
    public java.util.List<VerificationRequest> getRequestsByCredential(
            Long credentialId) {
        return requestRepo.findByCredentialId(credentialId);
    }
}
