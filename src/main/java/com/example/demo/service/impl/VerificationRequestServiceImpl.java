package com.example.demo.service.impl;

import com.example.demo.entity.CredentialRecord;
import com.example.demo.entity.VerificationRequest;
import com.example.demo.repository.CredentialRecordRepository;
import com.example.demo.repository.VerificationRequestRepository;
import com.example.demo.service.VerificationRequestService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VerificationRequestServiceImpl implements VerificationRequestService {

    private final VerificationRequestRepository requestRepo;
    private final CredentialRecordRepository credentialRepo;

    public VerificationRequestServiceImpl(
            VerificationRequestRepository requestRepo,
            CredentialRecordRepository credentialRepo) {

        this.requestRepo = requestRepo;
        this.credentialRepo = credentialRepo;
    }

    // ✅ REQUIRED BY INTERFACE
    @Override
    public VerificationRequest initiateVerification(VerificationRequest request) {
        return requestRepo.save(request);
    }

    // ✅ REQUIRED BY INTERFACE
    @Override
    public VerificationRequest processVerification(Long credentialId) {

        CredentialRecord credential =
                credentialRepo.findById(credentialId).orElse(null);

        if (credential == null) return null;

        VerificationRequest req = new VerificationRequest();
        req.setCredentialId(credentialId);

        if (credential.getExpiryDate() != null &&
            credential.getExpiryDate().isBefore(LocalDate.now())) {

            req.setStatus("FAILED");
            return requestRepo.save(req);
        }

        req.setStatus("SUCCESS");
        return requestRepo.save(req);
    }

    // ✅ REQUIRED BY INTERFACE
    @Override
    public List<VerificationRequest> getRequestsByCredential(Long credentialId) {
        return requestRepo.findByCredentialId(credentialId);
    }
}
