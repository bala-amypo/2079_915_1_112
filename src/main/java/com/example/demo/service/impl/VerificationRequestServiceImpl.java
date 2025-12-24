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

    private final VerificationRequestRepository requestRepo;
    private final AuditTrailRecordRepository auditRepo;

    public VerificationRequestServiceImpl(
            VerificationRequestRepository requestRepo,
            AuditTrailRecordRepository auditRepo) {
        this.requestRepo = requestRepo;
        this.auditRepo = auditRepo;
    }

    @Override
    public VerificationRequest createRequest(VerificationRequest request) {
        request.setRequestedAt(LocalDateTime.now());
        return requestRepo.save(request);
    }

    @Override
    public VerificationRequest processVerification(Long requestId) {
        VerificationRequest request = requestRepo.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        request.setStatus("VERIFIED");
        requestRepo.save(request);

        AuditTrailRecord audit = new AuditTrailRecord();
        audit.setCredentialId(request.getCredentialId());
        audit.setAction("VERIFICATION_PROCESSED");
        audit.setPerformedBy("SYSTEM");

        // ✅ NOW THIS WORKS
        audit.setTimestamp(LocalDateTime.now());

        auditRepo.save(audit);
        return request;
    }

    @Override
    public List<VerificationRequest> getRequestsByCredential(Long credentialId) {
        return requestRepo.findByCredentialId(credentialId);
    }
}
