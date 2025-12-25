package com.example.demo.service.impl;

import com.example.demo.entity.CredentialRecord;
import com.example.demo.repository.CredentialRecordRepository;
import com.example.demo.service.VerificationRequestService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class VerificationRequestServiceImpl implements VerificationRequestService {

    private final CredentialRecordRepository repo;

    public VerificationRequestServiceImpl(CredentialRecordRepository repo) {
        this.repo = repo;
    }

    @Override
    public String processVerification(Long credentialId) {

        CredentialRecord credential = repo.findById(credentialId).orElse(null);
        if (credential == null) return null;

        if (credential.getExpiryDate() != null &&
            credential.getExpiryDate().isBefore(LocalDate.now())) {

            credential.setStatus("EXPIRED");
            repo.save(credential);
            return "FAILED";
        }

        return "SUCCESS";
    }
}
