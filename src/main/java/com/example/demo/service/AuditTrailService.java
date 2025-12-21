


package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.AuditTrail;

public interface AuditTrailService {

    List<AuditTrail> getLogsByCredential(Long credentialId);

    List<AuditTrail> getAllLogs();

    AuditTrail save(AuditTrail auditTrail);
}
