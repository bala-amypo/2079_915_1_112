package com.example.demo.service.impl;

import com.example.demo.entity.AuditTrailRecord;
import com.example.demo.repository.AuditTrailRepository;
import com.example.demo.service.AuditTrailService;
import org.springframework.stereotype.Service;

@Service
public class AuditTrailServiceImpl implements AuditTrailService {

    private final AuditTrailRepository repository;

    public AuditTrailServiceImpl(AuditTrailRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(AuditTrailRecord record) {
        repository.save(record);
    }
}
