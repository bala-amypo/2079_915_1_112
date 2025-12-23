package com.example.demo.service.impl;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CredentialRecord;
import com.example.demo.entity.VerificationRequest;
import com.example.demo.enums.VerificationStatus;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.VerificationRequestRepository;
import com.example.demo.service.AuditTrailService;
import com.example.demo.service.CredentialRecordService;
import com.example.demo.service.VerificationRequestService;
import com.example.demo.service.VerificationRuleService;

@Service
public class VerificationRequestServiceImpl implements VerificationRequestService {

    private final VerificationRequestRepository requestRepository;
    private final CredentialRecordService credentialService;
    private final VerificationRuleService ruleService;
    private final AuditTrailService auditService;

    // =========================================================
    // ✅ CONSTRUCTOR USED BY SPRING BOOT (IMPORTANT)
    // =========================================================
    @Autowired
    public VerificationRequestServiceImpl(
            VerificationRequestRepository requestRepository,
            CredentialRecordService credentialService,
            AuditTrailService auditService) {

        this.requestRepository = requestRepository;
        this.credentialService = credentialService;
        this.auditService = auditService;
        this.ruleService = null; // rules not required for runtime wiring
    }

    // =========================================================
    // ✅ CONSTRUCTOR USED BY UNIT TESTS (DO NOT AUTOWIRE)
    // =========================================================
    public VerificationRequestServiceImpl(
            VerificationRequestRepository requestRepository,
            CredentialRecordService credentialService,
            VerificationRuleService ruleService,
            AuditTrailService auditService) {

        this.requestRepository = requestRepository;
        this.credentialService = credentialService;
        this.ruleService = ruleService;
        this.auditService = auditService;
    }

    // =========================================================
    // BUSINESS METHODS
    // =========================================================

    @Override
    public VerificationRequest createRequest(VerificationRequest request) {

        if (request.getCredentialId() == null) {
            throw new IllegalArgumentException("Credential ID is required");
        }

        CredentialRecord record = credentialService.getById(request.getCredentialId())
                .orElseThrow(() -> new ResourceNotFoundException("Credential not found"));

        // Expiry check
        if (record.getExpiryDate() != null &&
            record.getExpiryDate().isBefore(LocalDate.now())) {

            request.setStatus(VerificationStatus.FAILED);
            auditService.log("Credential expired for ID " + record.getId());
        } else {
            request.setStatus(VerificationStatus.SUCCESS);
            auditService.log("Credential verified successfully for ID " + record.getId());
        }

        return requestRepository.save(request);
    }

    @Override
    public VerificationRequest getById(Long id) {
        return requestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Verification request not found"));
    }

    @Override
    public VerificationStatus processVerification(Long requestId) {

        VerificationRequest request = getById(requestId);

        if (request.getStatus() != null) {
            return request.getStatus();
        }

        CredentialRecord record = credentialService.getById(request.getCredentialId())
                .orElseThrow(() -> new ResourceNotFoundException("Credential not found"));

        if (record.getExpiryDate() != null &&
            record.getExpiryDate().isBefore(LocalDate.now())) {

            request.setStatus(VerificationStatus.FAILED);
        } else {
            request.setStatus(VerificationStatus.SUCCESS);
        }

        requestRepository.save(request);
        return request.getStatus();
    }
}
