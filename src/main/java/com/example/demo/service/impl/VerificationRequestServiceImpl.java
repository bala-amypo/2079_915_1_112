package com.example.demo.service.impl;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.example.demo.entity.VerificationRequest;
import com.example.demo.repository.CredentialRecordRepository;
import com.example.demo.repository.VerificationRequestRepository;
import com.example.demo.service.AuditTrailService;
import com.example.demo.service.VerificationRequestService;

@Service
public class VerificationRequestServiceImpl implements VerificationRequestService {

    private final VerificationRequestRepository verificationRepo;
    private final CredentialRecordRepository credentialRepo;
    private final AuditTrailService auditService;

    // ✅ EXACT constructor expected by tests
    public VerificationRequestServiceImpl(
            VerificationRequestRepository verificationRepo,
            CredentialRecordRepository credentialRepo,
            AuditTrailService auditService) {

        this.verificationRepo = verificationRepo;
        this.credentialRepo = credentialRepo;
        this.auditService = auditService;
    }

    @Override
    public VerificationRequest save(VerificationRequest request) {
        return verificationRepo.save(request);
    }

    @Override
    public void processExpiredCredentials() {
        credentialRepo.findExpiredBefore(LocalDate.now());
    }
}
