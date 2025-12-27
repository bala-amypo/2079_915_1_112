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
public class VerificationRequestServiceImpl
        implements VerificationRequestService {

    private final VerificationRequestRepository repository;
    private final AuditTrailService auditTrailService;

    public VerificationRequestServiceImpl(
            VerificationRequestRepository repository,
            AuditTrailService auditTrailService) {
        this.repository = repository;
        this.auditTrailService = auditTrailService;
    }

    @Override
    public VerificationRequest initiateVerification(
            VerificationRequest request) {

        request.setRequestedAt(LocalDateTime.now());
        request.setStatus("PENDING");

        VerificationRequest saved = repository.save(request);

        AuditTrailRecord audit = new AuditTrailRecord();
        audit.setAction("VERIFICATION_REQUEST_CREATED");
        audit.setCredentialId(
                saved.getCredentialRecord().getId()
        );

        auditTrailService.logEvent(audit);

        return saved;
    }

    @Override
    public List<VerificationRequest> getRequestsByCredential(
            Long credentialId) {

        return repository.findByCredentialRecordId(credentialId);
    }

    @Override
public List<VerificationRequest> getRequestsByCredential(Long credentialId) {
    return repository.findByCredentialId(credentialId);
}

}
