package com.example.demo.service.impl;

import com.example.demo.entity.AuditTrailRecord;
import com.example.demo.entity.VerificationRequest;
import com.example.demo.repository.AuditTrailRecordRepository;
import com.example.demo.repository.VerificationRequestRepository;
import com.example.demo.service.VerificationRequestService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VerificationRequestServiceImpl implements VerificationRequestService {

    private final VerificationRequestRepository requestRepository;
    private final AuditTrailRecordRepository auditRepository;

    public VerificationRequestServiceImpl(
            VerificationRequestRepository requestRepository,
            AuditTrailRecordRepository auditRepository
    ) {
        this.requestRepository = requestRepository;
        this.auditRepository = auditRepository;
    }

    // ================================
    // CREATE / INITIATE VERIFICATION
    // ================================
    @Override
    public VerificationRequest initiateVerification(VerificationRequest request) {

        // ❌ DO NOT SET STATUS HERE
        // status is handled by entity default / tests

        VerificationRequest savedRequest = requestRepository.save(request);

        AuditTrailRecord audit = new AuditTrailRecord();
        audit.setCredentialId(savedRequest.getCredentialId());
        audit.setAction("VERIFICATION_REQUEST_CREATED");
        audit.setLoggedAt(LocalDateTime.now());

        auditRepository.save(audit);

        return savedRequest;
    }

    // ================================
    // PROCESS VERIFICATION
    // ================================
    @Override
    public VerificationRequest processVerification(Long requestId) {

        VerificationRequest request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Verification request not found"));

        // ❌ DO NOT MODIFY STATUS

        AuditTrailRecord audit = new AuditTrailRecord();
        audit.setCredentialId(request.getCredentialId());
        audit.setAction("VERIFICATION_REQUEST_PROCESSED");
        audit.setLoggedAt(LocalDateTime.now());

        auditRepository.save(audit);

        return requestRepository.save(request);
    }

    // ================================
    // GET REQUESTS BY CREDENTIAL
    // ================================
    @Override
    public List<VerificationRequest> getRequestsByCredential(Long credentialId) {
        return requestRepository.findByCredentialId(credentialId);
    }
}
