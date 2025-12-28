package com.example.demo.service.impl;

import com.example.demo.entity.CredentialRecord;
import com.example.demo.repository.CredentialRecordRepository;
import com.example.demo.service.CredentialRecordService;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CredentialRecordServiceImpl implements CredentialRecordService {

    private final CredentialRecordRepository repo;

    public CredentialRecordServiceImpl(CredentialRecordRepository repo) {
        this.repo = repo;
    }

    @Override
    public CredentialRecord createCredential(CredentialRecord record) {

        if (record.getExpiryDate() != null &&
                record.getExpiryDate().isBefore(LocalDate.now())) {
            record.setStatus("EXPIRED");
        } else if (record.getStatus() == null) {
            record.setStatus("VALID");
        }

        return repo.save(record);
    }

    @Override
    public CredentialRecord updateCredential(Long id, CredentialRecord update) {
        CredentialRecord existing = repo.findById(id).orElseThrow();
        existing.setCredentialCode(update.getCredentialCode());
        return repo.save(existing);
    }

    @Override
    public List<CredentialRecord> getCredentialsByHolder(Long holderId) {
        return repo.findByHolderId(holderId);
    }

    @Override
    public CredentialRecord getCredentialByCode(String code) {
        return repo.findByCredentialCode(code).orElse(null);
    }

    @Override
    public List<CredentialRecord> findAll() {
        return repo.findAll();
    }
}
