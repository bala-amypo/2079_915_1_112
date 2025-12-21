package com.example.demo.service;

import com.example.demo.entity.AuditTrailRecord;
import java.util.List;

public interface AuditTrailService {

    AuditTrailRecord save(AuditTrailRecord record);

    List<AuditTrailRecord> getAll();
}
