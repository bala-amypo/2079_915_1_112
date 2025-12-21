package com.example.demo.service.impl;

import com.example.demo.entity.CredentialRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CredentialRecordRepository;
import com.example.demo.service.CredentialRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialRecordServiceImpl
        implements CredentialRecordService {

    private final CredentialRecordRepository repository;

    public CredentialRecordServiceImpl(
            CredentialRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public CredentialRecord createCredential(CredentialRecord credential) {
        return repository.save(credential);
    }

    @Override
    public CredentialRecord updateCredential(Long id, CredentialRecord credential) {
        CredentialRecord existing = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Credential not found"));

        existing.setCredentialName(credential.getCredentialName());
        existing.setExpiryDate(credential.getExpiryDate());
        existing.setUser(credential.getUser());

        return repository.save(existing);
    }

    @Override
    public List<CredentialRecord> getByUserId(Long userId) {
        return repository.findByUser_Id(userId);
    }

    @Override
    public CredentialRecord getByCredentialCode(String credentialCode) {
        return repository.findByCredentialCode(credentialCode)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Credential not found"));
    }

    @Override
    public List<CredentialRecord> getAllCredentials() {
        return repository.findAll();
    }
}
