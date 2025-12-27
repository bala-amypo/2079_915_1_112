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
    public CredentialRecord createCredential(CredentialRecord record) {
        return repository.save(record);
    }

    @Override
    public CredentialRecord updateCredential(Long id, CredentialRecord update) {
        CredentialRecord existing = getById(id);
        existing.setStatus(update.getStatus());
        existing.setExpiryDate(update.getExpiryDate());
        return repository.save(existing);
    }

    @Override
    public List<CredentialRecord> getCredentialsByHolder(Long holderId) {
        return repository.findByHolderId(holderId);
    }

    @Override
    public CredentialRecord getCredentialByCode(String code) {
        return repository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Credential not found"));
    }

    @Override
    public CredentialRecord getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Credential not found"));
    }
}
