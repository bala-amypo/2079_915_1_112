package com.example.demo.service.impl;

import com.example.demo.entity.CredentialRecord;
import com.example.demo.repository.CredentialRecordRepository;
import com.example.demo.service.CredentialRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialRecordServiceImpl implements CredentialRecordService {

    private final CredentialRecordRepository repository;

    public CredentialRecordServiceImpl(CredentialRecordRepository repository) {
        this.repository = repository;
    }

    // CREATE
    @Override
    public CredentialRecord createCredential(CredentialRecord credential) {
        return repository.save(credential);
    }

    // READ BY ID
    @Override
    public CredentialRecord getCredentialById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Credential not found with id: " + id));
    }

    // READ ALL
    @Override
    public List<CredentialRecord> getAllCredentials() {
        return repository.findAll();
    }

    // READ BY HOLDER
    @Override
    public List<CredentialRecord> getCredentialsByHolder(Long holderId) {
        return repository.findByHolderId(holderId);
    }

    // UPDATE
    @Override
    public CredentialRecord updateCredential(Long id, CredentialRecord updated) {
        CredentialRecord existing = getCredentialById(id);

        existing.setCredentialCode(updated.getCredentialCode());
        existing.setIssuer(updated.getIssuer());
        existing.setExpiryDate(updated.getExpiryDate());
        existing.setMetadataJson(updated.getMetadataJson());
        existing.setHolderId(updated.getHolderId());

        return repository.save(existing);
    }

    // DELETE
    @Override
    public void deleteCredential(Long id) {
        repository.deleteById(id);
    }
}
