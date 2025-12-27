package com.example.demo.service.impl;

import com.example.demo.entity.CredentialRecord;
import com.example.demo.repository.CredentialRecordRepository;
import com.example.demo.service.CredentialRecordService;
import org.springframework.stereotype.Service;

@Service
public class CredentialRecordServiceImpl implements CredentialRecordService {

    private final CredentialRecordRepository repository;

    public CredentialRecordServiceImpl(CredentialRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public CredentialRecord getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Credential not found"));
    }
}
