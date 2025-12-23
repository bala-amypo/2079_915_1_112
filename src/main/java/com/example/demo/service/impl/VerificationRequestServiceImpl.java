package com.example.demo.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.AuditTrailRecord;
import com.example.demo.entity.CredentialRecord;
import com.example.demo.entity.VerificationRequest;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.VerificationRequestRepository;
import com.example.demo.service.AuditTrailService;
import com.example.demo.service.CredentialRecordService;
import com.example.demo.service.VerificationRequestService;

@Service
public class VerificationRequestServiceImpl implements VerificationRequestService {

    private VerificationRequestRepository requestRepository;
    private CredentialRecordService credentialService;
    private AuditTrailService auditService;

    // ✅ REQUIRED DEFAULT CONSTRUCTOR
    public VerificationRequestServiceImpl() {
    }

    // ✅ SPRING CONSTRUCTOR INJECTION
    public VerificationRequestServiceImpl(
            VerificationRequestRepository requestRepository,
            CredentialRecordService credentialService,
            AuditTrailService auditService) {

        this.requestRepository = requestRepository;
        this.credentialService = credentialService;
        this.auditService = auditService;
    }

    // =====================================================
    // CREATE VERIFICATION REQUEST
    // =====================================================
    @Override
    public VerificationRequest initiateVerification(VerificationRequest request) {
        request.setStatus("PENDING");
        return requestRepository.save(request);
    }

    // =====================================================
    // PROCESS VERIFICATION
    // =====================================================
    @Override
    public VerificationRequest processVerification(Long requestId) {

        VerificationRequest request = requestRepository.findById(requestId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Verification request not found"));

        // ✅ USE CORRECT SERVICE METHOD
        CredentialRecord record = credentialService.getCredential(request.getCredentialId());

        // ✅ EXPIRY CHECK (TEST EXPECTS THIS)
        if (record.getExpiryDate() != null &&
            record.getExpiryDate().isBefore(LocalDate.now())) {

            request.setStatus("FAILED");
        } else {
            request.setStatus("SUCCESS");
        }

        // ✅ AUDIT TRAIL (CORRECT FIELD)
        AuditTrailRecord audit = new AuditTrailRecord();
        audit.setCredentialId(record.getId());
        audit.setEvent("VERIFICATION_" + request.getStatus());
        auditService.logEvent(audit);

        return requestRepository.save(request);
    }

    // =====================================================
    // GET REQUESTS BY CREDENTIAL
    // =====================================================
    @Override
    public List<VerificationRequest> getRequestsByCredential(Long credentialId) {
        return requestRepository.findByCredentialId(credentialId);
    }
}
