package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.AuditTrailRecord;
import com.example.demo.repository.AuditTrailRecordRepository;
import com.example.demo.service.AuditTrailService;

@Service
public class AuditTrailServiceImpl implements AuditTrailService {

    private final AuditTrailRecordRepository repository;

    /*
     * REQUIRED BY TEST CASE:
     * new AuditTrailServiceImpl(auditTrailRepository)
     */
    public AuditTrailServiceImpl(AuditTrailRecordRepository repository) {
        this.repository = repository;
    }

    /*
     * REQUIRED BY SPRING
     */
    public AuditTrailServiceImpl() {
        this.repository = null;
    }

    @Override
    public AuditTrailRecord save(AuditTrailRecord record) {
        if (record.getTimestamp() == null) {
            record.setTimestamp(LocalDateTime.now());
        }
        return repository.save(record);
    }

    @Override
    public List<AuditTrailRecord> getAll() {
        return repository.findAll();
    }

    @Override
    public List<AuditTrailRecord> getLogsByCredential(Long credentialId) {
        return repository.findByCredentialId(credentialId);
    }
}
