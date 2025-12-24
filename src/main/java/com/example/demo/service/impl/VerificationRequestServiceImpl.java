package com.example.demo.service.impl;

import com.example.demo.entity.AuditTrailRecord;
import com.example.demo.entity.VerificationRequest;
import com.example.demo.repository.VerificationRequestRepository;
import com.example.demo.service.AuditTrailService;
import com.example.demo.service.VerificationRequestService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VerificationRequestServiceImpl implements VerificationRequestService {

    private final VerificationRequestRepository repository;
    private final AuditTrailService auditService;

    public VerificationRequestServiceImpl(
            VerificationRequestRepository repository,
            AuditTrailService auditService) {
        this.repository = repository;
        this.auditService = auditService;
    }

    @Override
    public VerificationRequest initiateVerification(VerificationRequest request) {
        request.setStatus("PENDING");
        VerificationRequest saved = repository.save(request);

        AuditTrailRecord audit = new AuditTrailRecord();
        audit.setAction("VERIFICATION_STARTED");
        audit.setTimestamp(LocalDateTime.now());

        auditService.logEvent(audit);
        return saved;
    }

    @Override
    public VerificationRequest processVerification(Long requestId) {
        VerificationRequest req = repository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Not found"));

        req.setStatus("VERIFIED");
        return repository.save(req);
    }

    @Override
    public VerificationRequest getRequestById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }

    @Override
    public List<VerificationRequest> getAllRequests() {
        return repository.findAll();
    }

    @Override
    public List<VerificationRequest> getRequestsByCredential(Long credentialId) {
        return repository.findAll();
    }
}
