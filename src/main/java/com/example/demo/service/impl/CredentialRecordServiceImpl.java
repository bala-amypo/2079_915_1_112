package com.example.demo.service.impl;

import com.example.demo.entity.CredentialRecord;
import com.example.demo.repository.CredentialRecordRepository;
import com.example.demo.service.CredentialRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialRecordServiceImpl implements CredentialRecordService {

    private final CredentialRecordRepository repo;

    public CredentialRecordServiceImpl(CredentialRecordRepository repo) {
        this.repo = repo;
    }

    @Override
    public CredentialRecord createCredential(CredentialRecord credential) {
        if (credential.getStatus() == null) {
            credential.setStatus("ACTIVE");
        }
        return repo.save(credential);
    }

    @Override
    public CredentialRecord getCredentialById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<CredentialRecord> getAllCredentials() {
        return repo.findAll();
    }

    @Override
    public List<CredentialRecord> getCredentialsByHolder(Long holderId) {
        return repo.findByHolderId(holderId);
    }

    @Override
    public CredentialRecord updateCredential(Long id, CredentialRecord updated) {
        CredentialRecord existing = repo.findById(id).orElse(null);
        if (existing == null) return null;

        // ✅ REQUIRED by test t14
        existing.setCredentialCode(updated.getCredentialCode());
        existing.setTitle(updated.getTitle());
        existing.setIssuer(updated.getIssuer());
        existing.setCredentialType(updated.getCredentialType());
        existing.setExpiryDate(updated.getExpiryDate());
        existing.setStatus(updated.getStatus());
        existing.setBody(updated.getBody());
        existing.setMetadataJson(updated.getMetadataJson());

        return repo.save(existing);
    }

    @Override
    public void deleteCredential(Long id) {
        repo.deleteById(id);
    }
}
