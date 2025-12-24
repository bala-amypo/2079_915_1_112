package com.example.demo.service.impl;

import com.example.demo.entity.CredentialRecord;
import com.example.demo.repository.CredentialRecordRepository;
import com.example.demo.service.CredentialRecordService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CredentialRecordServiceImpl
        implements CredentialRecordService {

    private final CredentialRecordRepository repository;

    public CredentialRecordServiceImpl(CredentialRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public CredentialRecord createCredential(CredentialRecord credential) {
        return repository.save(credential);
    }

    @Override
    public CredentialRecord updateCredential(Long id, CredentialRecord updated) {
        CredentialRecord existing = getCredentialById(id);

        existing.setIssuer(updated.getIssuer());
        existing.setType(updated.getType());
        existing.setExpiryDate(updated.getExpiryDate());
        existing.setStatus(updated.getStatus());
        existing.setMetadataJson(updated.getMetadataJson());

        return repository.save(existing);
    }

    @Override
    public void deleteCredential(Long id) {
        repository.deleteById(id);
    }

    @Override
    public CredentialRecord getCredentialById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Credential not found"));
    }

    @Override
    public CredentialRecord getCredentialByCode(String code) {
        return repository.findByCredentialCode(code)
                .orElseThrow(() -> new RuntimeException("Credential not found"));
    }

    @Override
    public List<CredentialRecord> getAllCredentials() {
        return repository.findAll();
    }

    @Override
    public List<CredentialRecord> getCredentialsByHolder(Long holderId) {
        return repository.findByHolderId(holderId);
    }

    @Override
    public List<CredentialRecord> getExpiredCredentials(LocalDate date) {
        return repository.findExpiredBefore(date);
    }
}
