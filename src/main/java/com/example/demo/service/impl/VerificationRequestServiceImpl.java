package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.AuditTrailRecord;
import com.example.demo.entity.CredentialRecord;
import com.example.demo.entity.VerificationRequest;
import com.example.demo.repository.CredentialRecordRepository;
import com.example.demo.repository.VerificationRequestRepository;
import com.example.demo.service.AuditTrailService;
import com.example.demo.service.VerificationRequestService;
import com.example.demo.service.VerificationRuleService;

@Service
public class VerificationRequestServiceImpl implements VerificationRequestService {

    private final VerificationRequestRepository verificationRequestRepository;
    private final CredentialRecordRepository credentialRecordRepository;
    private final VerificationRuleService verificationRuleService;
    private final AuditTrailService auditTrailService;

    public VerificationRequestServiceImpl(
            VerificationRequestRepository verificationRequestRepository,
            CredentialRecordRepository credentialRecordRepository,
            VerificationRuleService verificationRuleService,
            AuditTrailService auditTrailService) {

        this.verificationRequestRepository = verificationRequestRepository;
        this.credentialRecordRepository = credentialRecordRepository;
        this.verificationRuleService = verificationRuleService;
        this.auditTrailService = auditTrailService;
    }

    // ✅ REQUIRED BY INTERFACE
    @Override
    public VerificationRequest initiateVerification(VerificationRequest request) {

        request.setRequestedAt(LocalDateTime.now());
        VerificationRequest savedRequest = verificationRequestRepository.save(request);

        CredentialRecord credential = credentialRecordRepository
                .findById(request.getCredentialId())
                .orElseThrow(() -> new RuntimeException("Credential not found"));

        boolean valid = verificationRuleService.validateCredential(credential);

        AuditTrailRecord audit = new AuditTrailRecord();
        audit.setAction(valid ? "VERIFICATION_SUCCESS" : "VERIFICATION_FAILED");
        audit.setTimestamp(LocalDateTime.now());

        auditTrailService.save(audit);

        return savedRequest;
    }

    // ✅ REQUIRED BY INTERFACE
    @Override
    public List<VerificationRequest> getRequestsByCredential(Long credentialId) {
        return verificationRequestRepository.findByCredentialId(credentialId);
    }

    // ❌ NOT IN INTERFACE → NO @Override
    public VerificationRequest getRequestById(Long id) {
        return verificationRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Verification request not found"));
    }

    // ❌ NOT IN INTERFACE → NO @Override
    public List<VerificationRequest> getAllRequests() {
        return verificationRequestRepository.findAll();
    }
}
