package com.example.demo.service;

import com.example.demo.entity.AuditTrailRecord;
import java.util.List;

public interface AuditTrailService {

    AuditTrailRecord save(AuditTrailRecord record);

    AuditTrailRecord logEvent(AuditTrailRecord record);

    List<AuditTrailRecord> getLogsByCredential(Long credentialId);
}
