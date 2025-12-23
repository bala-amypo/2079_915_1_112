package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.CredentialRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CredentialRecordRepository;
import com.example.demo.service.CredentialRecordService;

@Service
public class CredentialRecordServiceImpl implements CredentialRecordService {

    // ✅ Repository field (needed by tests + other services)
    private final CredentialRecordRepository repository;

    // ✅ Constructor used by Spring & tests
    public CredentialRecordServiceImpl(CredentialRecordRepository repository) {
        this.repository = repository;
    }

    // ✅ REQUIRED by VerificationRequestServiceImpl (test workaround)
    public CredentialRecordRepository getRepository() {
        return this.repository;
    }

    // ✅ Create credential
    @Override
    public CredentialRecord createCredential(CredentialRecord record) {
        return repository.save(record);
    }

    // ✅ Get credential by ID
    @Override
    public CredentialRecord getCredentialById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Credential not found"));
    }

    // ✅ Get credentials by holder ID
    @Override
    public List<CredentialRecord> getCredentialsByHolder(Long holderId) {
        return repository.findByHolderId(holderId);
    }

    // ✅ Get all credentials
    @Override
    public List<CredentialRecord> getAllCredentials() {
        return repository.findAll();
    }

    // ✅ Update credential
    @Override
    public CredentialRecord updateCredential(Long id, CredentialRecord updated) {

        CredentialRecord existing = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Credential not found"));

        existing.setCredentialName(updated.getCredentialName());
        existing.setCredentialCode(updated.getCredentialCode());
        existing.setExpiryDate(updated.getExpiryDate());
        existing.setIssuer(updated.getIssuer());
        existing.setMetadataJson(updated.getMetadataJson());

        return repository.save(existing);
    }

    // ✅ Delete credential
    @Override
    public void deleteCredential(Long id) {

        CredentialRecord existing = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Credential not found"));

        repository.delete(existing);
    }
}
