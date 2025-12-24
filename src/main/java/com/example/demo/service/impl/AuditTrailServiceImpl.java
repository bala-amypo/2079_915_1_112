package com.example.demo.service.impl;

import com.example.demo.entity.AuditTrailRecord;
import com.example.demo.repository.AuditTrailRecordRepository;
import com.example.demo.service.AuditTrailService;
import org.springframework.stereotype.Service;

@Service
public class AuditTrailServiceImpl implements AuditTrailService {

    private final AuditTrailRecordRepository repository;

    public AuditTrailServiceImpl(AuditTrailRecordRepository repository) {
        this.repository = repository;
    }

    /**
     * REQUIRED by AuditTrailService
     */
    @Override
    public void logEvent(AuditTrailRecord record) {
        repository.save(record);
    }
}
