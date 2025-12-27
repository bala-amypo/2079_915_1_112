package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.VerificationRequestRepository;
import com.example.demo.service.*;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VerificationRequestServiceImpl
        implements VerificationRequestService {

    private final VerificationRequestRepository requestRepository;
    private final CredentialRecordService credentialService;
    private final AuditTrailService auditTrailService;

    public VerificationRequestServiceImpl(
            VerificationRequestRepository requestRepository,
            CredentialRecordService credentialService,
            AuditTrailService auditTrailService) {

        this.requestRepository = requestRepository;
        this.credentialService = credentialService;
        this.auditTrailService = auditTrailService;
    }

    @Override
    public VerificationRequest initiateVerification(
            VerificationRequest request) {

        // ✅ get credential safely
        CredentialRecord credential =
                credentialService.getById(
                        request.getCredentialId());

        request.setStatus("PENDING");

        VerificationRequest saved =
                requestRepository.save(request);

        // ✅ audit entry (NOW WORKS)
        AuditTrailRecord audit =
                new AuditTrailRecord(
                        "VERIFICATION_STARTED",
                        credential.getId());

        auditTrailService.logEvent(audit);

        return saved;
    }

    @Override
    public List<VerificationRequest> getRequestsByCredential(
            Long credentialId) {

        return requestRepository
                .findByCredentialId(credentialId);
    }
}
