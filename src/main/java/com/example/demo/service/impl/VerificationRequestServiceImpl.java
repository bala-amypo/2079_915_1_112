package com.example.demo.service.impl;

import com.example.demo.entity.AuditTrailRecord;
import com.example.demo.entity.CredentialRecord;
import com.example.demo.entity.VerificationRequest;
import com.example.demo.repository.CredentialRecordRepository;
import com.example.demo.repository.VerificationRequestRepository;
import com.example.demo.service.AuditTrailService;
import com.example.demo.service.VerificationRequestService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VerificationRequestServiceImpl implements VerificationRequestService {

    private final VerificationRequestRepository requestRepository;
    private final CredentialRecordRepository credentialRepository;
    private final AuditTrailService auditTrailService;

    public VerificationRequestServiceImpl(
            VerificationRequestRepository requestRepository,
            CredentialRecordRepository credentialRepository,
            AuditTrailService auditTrailService) {

        this.requestRepository = requestRepository;
        this.credentialRepository = credentialRepository;
        this.auditTrailService = auditTrailService;
    }

    // ================= CREATE / INITIATE =================

    @Override
    public VerificationRequest initiateVerification(VerificationRequest request) {

        // Ensure credential exists
        CredentialRecord credential = credentialRepository.findById(
                request.getCredentialId()
        ).orElseThrow(() -> new RuntimeException("Credential not found"));

        // Save verification request
        request.setStatus("PENDING");
        VerificationRequest savedRequest = requestRepository.save(request);

        // Log audit event
        AuditTrailRecord audit = new AuditTrailRecord();
        audit.setCredentialId(credential.getId());
        audit.setAction("VERIFICATION_INITIATED");
        audit.setTimestamp(LocalDateTime.now());

        auditTrailService.logEvent(audit);

        return savedRequest;
    }

    // ================= PROCESS =================

    @Override
    public VerificationRequest processVerification(Long requestId) {

        VerificationRequest request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Verification request not found"));

        request.setStatus("VERIFIED");
        VerificationRequest updated = requestRepository.save(request);

        // Audit log
        AuditTrailRecord audit = new AuditTrailRecord();
        audit.setCredentialId(request.getCredentialId());
        audit.setAction("VERIFICATION_COMPLETED");
        audit.setTimestamp(LocalDateTime.now());

        auditTrailService.logEvent(audit);

        return updated;
    }

    // ================= READ =================

    @Override
    public VerificationRequest getRequestById(Long id) {
        return requestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Verification request not found"));
    }

    @Override
    public List<VerificationRequest> getAllRequests() {
        return requestRepository.findAll();
    }

    @Override
    public List<VerificationRequest> getRequestsByCredential(Long credentialId) {
        return requestRepository.findAll().stream()
                .filter(r -> credentialId.equals(r.getCredentialId()))
                .toList();
    }
}
