package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.AuditTrailRecord;
import com.example.demo.entity.VerificationRequest;
import com.example.demo.repository.AuditTrailRecordRepository;
import com.example.demo.repository.VerificationRequestRepository;
import com.example.demo.service.VerificationRequestService;

@Service
public class VerificationRequestServiceImpl implements VerificationRequestService {

    private final VerificationRequestRepository requestRepository;
    private final AuditTrailRecordRepository auditRepository;

    public VerificationRequestServiceImpl(
            VerificationRequestRepository requestRepository,
            AuditTrailRecordRepository auditRepository) {
        this.requestRepository = requestRepository;
        this.auditRepository = auditRepository;
    }

    // ===============================
    // INITIATE VERIFICATION
    // ===============================
    @Override
    public VerificationRequest initiateVerification(VerificationRequest request) {

        // ✅ FIX: use INNER ENUM, not String
        request.setStatus(VerificationRequest.VerificationStatus.PENDING);
        request.setRequestedAt(LocalDateTime.now());

        VerificationRequest saved = requestRepository.save(request);

        AuditTrailRecord audit = new AuditTrailRecord();
        audit.setAction("VERIFICATION_REQUESTED");
        audit.setCredentialId(request.getCredentialId());
        audit.setDetails("Verification initiated");
        auditRepository.save(audit);

        return saved;
    }

    // ===============================
    // PROCESS VERIFICATION
    // ===============================
    @Override
    public VerificationRequest processVerification(Long requestId) {

        VerificationRequest request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        // ✅ FIX: use INNER ENUM, not String
        request.setStatus(VerificationRequest.VerificationStatus.VERIFIED);

        VerificationRequest updated = requestRepository.save(request);

        AuditTrailRecord audit = new AuditTrailRecord();
        audit.setAction("VERIFICATION_COMPLETED");
        audit.setCredentialId(request.getCredentialId());
        audit.setDetails("Verification completed successfully");
        auditRepository.save(audit);

        return updated;
    }

    // ===============================
    // GET REQUESTS BY CREDENTIAL
    // ===============================
    @Override
    public List<VerificationRequest> getRequestsByCredential(Long credentialId) {
        return requestRepository.findByCredentialId(credentialId);
    }
}
