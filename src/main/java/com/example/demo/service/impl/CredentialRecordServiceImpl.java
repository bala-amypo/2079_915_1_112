package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.CredentialRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CredentialRecordRepository;
import com.example.demo.service.CredentialRecordService;

@Service
public class CredentialRecordServiceImpl implements CredentialRecordService {

    private final CredentialRecordRepository repository;

    public CredentialRecordServiceImpl(CredentialRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public CredentialRecord create(CredentialRecord record) {
        return repository.save(record);
    }

    @Override
    public CredentialRecord getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Credential not found"));
    }

    @Override
    public List<CredentialRecord> getAllCredentials() {
        return repository.findAll();
    }

    // ✅ THIS METHOD NAME MUST MATCH INTERFACE
    @Override
    public List<CredentialRecord> getCredentialsByHolder(Long holderId) {
        return repository.findByHolderId(holderId);
    }

    @Override
    public CredentialRecord getCredentialByCode(String code) {
        return repository.findByCredentialCode(code)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Credential not found"));
    }

    @Override
    public CredentialRecord updateCredential(Long id, CredentialRecord updated) {
        CredentialRecord existing = getById(id);
        existing.setStatus(updated.getStatus());
        existing.setExpiryDate(updated.getExpiryDate());
        return repository.save(existing);
    }

    @Override
    public void deleteCredential(Long id) {
        CredentialRecord existing = getById(id);
        repository.delete(existing);
    }
}
