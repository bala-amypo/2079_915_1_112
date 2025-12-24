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
            AuditTrailRecordRepository auditRepository) {
        this.requestRepository = requestRepository;
        this.auditRepository = auditRepository;
    }

    // ✅ REQUIRED BY INTERFACE
    @Override
    public VerificationRequest initiateVerification(VerificationRequest request) {
        request.setRequestedAt(LocalDateTime.now());
        request.setStatus("PENDING");
        return requestRepository.save(request);
    }

    // ✅ REQUIRED BY INTERFACE
    @Override
    public VerificationRequest processVerification(Long requestId) {

        VerificationRequest request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Verification request not found"));

        request.setStatus("VERIFIED");
        requestRepository.save(request);

        AuditTrailRecord audit = new AuditTrailRecord();
        audit.setCredentialId(request.getCredentialId());
        audit.setAction("VERIFICATION_PROCESSED");
        audit.setPerformedBy("SYSTEM");

        // This now WORKS (alias method exists)
        audit.setTimestamp(LocalDateTime.now());

        auditRepository.save(audit);

        return request;
    }

    // ✅ REQUIRED BY INTERFACE
    @Override
    public List<VerificationRequest> getRequestsByCredential(Long credentialId) {
        return requestRepository.findByCredentialId(credentialId);
    }
}
