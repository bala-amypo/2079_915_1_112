package com.example.demo.repository;

import java.util.*;
import com.example.demo.entity.*;

public interface AuditTrailRecordRepository {
    AuditTrailRecord save(AuditTrailRecord r);
    List<AuditTrailRecord> findByCredentialId(Long id);
}
