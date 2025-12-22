package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.VerificationRequestService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VerificationRequestServiceImpl
        implements VerificationRequestService {

    private final VerificationRequestRepository requestRepo;
    private final CredentialRecordRepository credentialRepo;
    private final AuditTrailRecordRepository auditRepo;

    public VerificationRequestServiceImpl(
            VerificationRequestRepository requestRepo,
            CredentialRecordRepository credentialRepo,
            AuditTrailRecordRepository auditRepo) {
        this.requestRepo = requestRepo;
        this.credentialRepo = credentialRepo;
        this.auditRepo = auditRepo;
    }

    @Override
    public VerificationRequest initiateVerification(
            VerificationRequest request) {
        request.setStatus("PENDING");
        return requestRepo.save(request);
    }

    @Override
    public VerificationRequest processVerification(Long requestId) {

        VerificationRequest request = requestRepo.findById(requestId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Verification request not found"));

        CredentialRecord credential = credentialRepo
                .findById(request.getCredentialId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Credential not found"));

        if (credential.getExpiryDate() != null &&
                credential.getExpiryDate().isBefore(LocalDate.now())) {
            request.setStatus("FAILED");
        } else {
            request.setStatus("SUCCESS");
        }

        AuditTrailRecord audit = new AuditTrailRecord();
        audit.setCredentialId(credential.getId());
        audit.setAction("VERIFICATION_PROCESSED");
        auditRepo.save(audit);

        return requestRepo.save(request);
    }

    @Override
    public List<VerificationRequest> getRequestsByCredential(
            Long credentialId) {
        return requestRepo.findByCredentialId(credentialId);
    }

    @Override
    public VerificationRequest getById(Long id) {
        return requestRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Verification request not found"));
    }

    @Override
    public List<VerificationRequest> getAll() {
        return requestRepo.findAll();
    }
}
