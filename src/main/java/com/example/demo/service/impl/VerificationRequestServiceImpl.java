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

    private final VerificationRequestRepository repository;
    private final CredentialRecordService credentialService;
    private final AuditTrailService auditService;

    public VerificationRequestServiceImpl(
            VerificationRequestRepository repository,
            CredentialRecordService credentialService,
            AuditTrailService auditService) {

        this.repository = repository;
        this.credentialService = credentialService;
        this.auditService = auditService;
    }

    @Override
    public VerificationRequest initiateVerification(VerificationRequest request) {
        return repository.save(request);
    }

    @Override
    public VerificationRequest processVerification(Long requestId) {

        VerificationRequest request = repository.findById(requestId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Request not found"));

        // ✅ CORRECT METHOD NAME
        CredentialRecord credential = credentialService
                .getCredentialById(request.getCredentialId());

        if (credential.getExpiryDate() != null &&
            credential.getExpiryDate().isBefore(LocalDate.now())) {

            request.setStatus("FAILED");
        } else {
            request.setStatus("SUCCESS");
        }

        AuditTrailRecord audit = new AuditTrailRecord();
        audit.setCredentialId(request.getCredentialId());
        auditService.logEvent(audit);

        return repository.save(request);
    }

    @Override
    public List<VerificationRequest> getRequestsByCredential(Long credentialId) {
        return repository.findByCredentialId(credentialId);
    }
}
