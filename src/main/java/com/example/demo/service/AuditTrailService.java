package com.example.demo.service;

import com.example.demo.entity.AuditTrailRecord;

public interface AuditTrailService {
    void logEvent(AuditTrailRecord record);
}
