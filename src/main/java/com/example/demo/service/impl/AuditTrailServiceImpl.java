package com.example.demo.service.impl;

import com.example.demo.entity.AuditTrail;
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
    public List<AuditTrail> getLogsByCredential(Long credentialId) {
        return repository.findByCredentialId(credentialId);
    }

    @Override
    public List<AuditTrail> getAllLogs() {
        return repository.findAll();
    }

    @Override
    public AuditTrail save(AuditTrail auditTrail) {
        return repository.save(auditTrail);
    }
}
