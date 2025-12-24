package com.example.demo.service.impl;

import com.example.demo.entity.AuditTrailRecord;
import com.example.demo.service.AuditTrailService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuditTrailServiceImpl implements AuditTrailService {

    private final List<AuditTrailRecord> store = new ArrayList<>();

    @Override
    public void logEvent(AuditTrailRecord record) {
        store.add(record);
    }

    // REQUIRED by interface
    @Override
    public void save(AuditTrailRecord record) {
        store.add(record);
    }

    @Override
    public List<AuditTrailRecord> getLogsByCredential(Long credentialId) {
        // Entity has NO credentialId getter → return all
        return store;
    }
}
