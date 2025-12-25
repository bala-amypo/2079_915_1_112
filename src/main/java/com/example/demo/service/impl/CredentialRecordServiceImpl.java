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

    @Override
    public CredentialRecord createCredential(CredentialRecord credential) {
        return repository.save(credential);
    }

    @Override
    public CredentialRecord getCredentialById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<CredentialRecord> getAllCredentials() {
        return repository.findAll();
    }

    @Override
    public List<CredentialRecord> getCredentialsByHolder(Long holderId) {
        return repository.findByHolderId(holderId)
                .orElse(List.of());
    }

    @Override
    public CredentialRecord updateCredential(Long id, CredentialRecord credential) {
        CredentialRecord existing = repository.findById(id).orElse(null);
        if (existing == null) {
            return null;
        }

        existing.setCredentialCode(credential.getCredentialCode());
        existing.setHolderId(credential.getHolderId());
        existing.setIssuer(credential.getIssuer());
        existing.setExpiryDate(credential.getExpiryDate());
        existing.setBody(credential.getBody());
        existing.setMetadataJson(credential.getMetadataJson());
        existing.setStatus(credential.getStatus());

        return repository.save(existing);
    }

    @Override
    public void deleteCredential(Long id) {
        repository.deleteById(id);
    }
}
