package com.example.demo.service;

import com.example.demo.entity.AuditTrailRecord;
import java.util.List;

public interface AuditTrailService {

    void save(AuditTrailRecord record);

    void logEvent(AuditTrailRecord record);

    List<AuditTrailRecord> getLogsByCredential(Long credentialId);
}
