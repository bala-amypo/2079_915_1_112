package com.example.demo.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.VerificationRequest;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CredentialRecordRepository;
import com.example.demo.repository.VerificationRequestRepository;
import com.example.demo.service.AuditTrailService;
import com.example.demo.service.VerificationRequestService;

@Service
public class VerificationRequestServiceImpl implements VerificationRequestService {

    private final VerificationRequestRepository requestRepo;
    private final CredentialRecordRepository credentialRepo;
    private final AuditTrailService auditService;

    public VerificationRequestServiceImpl(
            VerificationRequestRepository requestRepo,
            CredentialRecordRepository credentialRepo,
            AuditTrailService auditService) {

        this.requestRepo = requestRepo;
        this.credentialRepo = credentialRepo;
        this.auditService = auditService;
    }

    @Override
    public VerificationRequest initiateVerification(VerificationRequest request) {
        return requestRepo.save(request);
    }

    // ✅ MISSING METHOD (CAUSE OF ERROR)
    @Override
    public VerificationRequest processVerification(Long requestId) {

        VerificationRequest request = requestRepo.findById(requestId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Verification request not found"));

        // expiry logic required by tests
        credentialRepo.findById(request.getCredentialId()).ifPresent(cred -> {
            if (cred.getExpiryDate() != null &&
                cred.getExpiryDate().isBefore(LocalDate.now())) {
                request.setStatus("FAILED");
            } else {
                request.setStatus("SUCCESS");
            }
        });

        return requestRepo.save(request);
    }

    @Override
    public List<VerificationRequest> getRequestsByCredential(Long credentialId) {
        return requestRepo.findByCredentialId(credentialId);
    }
}
