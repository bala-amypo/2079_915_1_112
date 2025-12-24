package com.example.demo.service.impl;

import com.example.demo.entity.CredentialRecord;
import com.example.demo.repository.CredentialRecordRepository;
import com.example.demo.service.CredentialRecordService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CredentialRecordServiceImpl implements CredentialRecordService {

    private final CredentialRecordRepository repository;

    public CredentialRecordServiceImpl(CredentialRecordRepository repository) {
        this.repository = repository;
    }

    // ================= CREATE =================
    @Override
    public CredentialRecord createCredential(CredentialRecord credential) {
        return repository.save(credential);
    }

    // ================= READ =================
    @Override
    public CredentialRecord getCredentialById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Credential not found with id: " + id));
    }

    @Override
    public List<CredentialRecord> getAllCredentials() {
        return repository.findAll();
    }

    @Override
    public List<CredentialRecord> getCredentialsByHolder(Long holderId) {
        return repository.findAll()
                .stream()
                .filter(c -> c.getUser() != null && holderId.equals(c.getUser().getId()))
                .toList();
    }

    @Override
    public CredentialRecord getCredentialByCode(String code) {
        return repository.findAll()
                .stream()
                .filter(c -> code.equals(c.getCredentialCode()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Credential not found with code: " + code));
    }

    // ================= UPDATE =================
    @Override
    public CredentialRecord updateCredential(Long id, CredentialRecord updated) {
        CredentialRecord existing = getCredentialById(id);

        existing.setCredentialCode(updated.getCredentialCode());
        existing.setCredentialName(updated.getCredentialName());
        existing.setExpiryDate(updated.getExpiryDate());
        existing.setStatus(updated.getStatus());
        existing.setMetadataJson(updated.getMetadataJson());

        return repository.save(existing);
    }

    // ================= DELETE =================
    @Override
    public void deleteCredential(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Credential not found with id: " + id);
        }
        repository.deleteById(id);
    }

    // ================= EXTRA (SAFE, NO QUERY METHODS) =================
    @Override
    public List<CredentialRecord> getExpiredCredentials(LocalDate date) {
        return repository.findAll()
                .stream()
                .filter(c -> c.getExpiryDate() != null && c.getExpiryDate().isBefore(date))
                .toList();
    }
}
