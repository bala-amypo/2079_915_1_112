package com.example.demo.service.impl;

import com.example.demo.entity.AuditTrailRecord;
import com.example.demo.repository.AuditTrailRepository;
import com.example.demo.service.AuditTrailService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditTrailServiceImpl implements AuditTrailService {

    private final AuditTrailRepository repository;

    public AuditTrailServiceImpl(AuditTrailRepository repository) {
        this.repository = repository;
    }

    @Override
    public AuditTrailRecord logEvent(AuditTrailRecord record) {
        return repository.save(record);
    }

    @Override
    public List<AuditTrailRecord> getLogsByCredential(Long credentialId) {
        return repository.findAll().stream()
                .filter(r -> credentialId.equals(r.getCredentialId()))
                .toList();
    }
}
