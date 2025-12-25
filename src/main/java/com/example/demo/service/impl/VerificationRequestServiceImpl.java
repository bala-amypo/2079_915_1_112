package com.example.demo.service.impl;

import com.example.demo.entity.CredentialRecord;
import com.example.demo.entity.VerificationRequest;
import com.example.demo.entity.VerificationRequest.VerificationStatus;
import com.example.demo.repository.CredentialRecordRepository;
import com.example.demo.repository.VerificationRequestRepository;
import com.example.demo.service.VerificationRequestService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class VerificationRequestServiceImpl implements VerificationRequestService {

    private final VerificationRequestRepository verificationRepo;
    private final CredentialRecordRepository credentialRepo;

    public VerificationRequestServiceImpl(
            VerificationRequestRepository verificationRepo,
            CredentialRecordRepository credentialRepo) {
        this.verificationRepo = verificationRepo;
        this.credentialRepo = credentialRepo;
    }

    @Override
    public VerificationRequest initiateVerification(VerificationRequest request) {
        request.setRequestedAt(LocalDateTime.now());
        return verificationRepo.save(request);
    }

    @Override
    public VerificationRequest processVerification(Long credentialId) {

        CredentialRecord credential = credentialRepo
                .findById(credentialId)
                .orElse(null);

        VerificationRequest req = new VerificationRequest();
        req.setCredentialId(credentialId);
        req.setRequestedAt(LocalDateTime.now());

        // ✅ TEST EXPECTATION LOGIC
        if (credential == null) {
            req.setStatus(VerificationStatus.FAILED);
        } else if (
                credential.getExpiryDate() != null &&
                credential.getExpiryDate().isBefore(LocalDate.now())
        ) {
            req.setStatus(VerificationStatus.FAILED);
        } else {
            req.setStatus(VerificationStatus.SUCCESS);
        }

        return verificationRepo.save(req);
    }

    @Override
    public List<VerificationRequest> getRequestsByCredential(Long credentialId) {
        return verificationRepo.findByCredentialId(credentialId);
    }
}
