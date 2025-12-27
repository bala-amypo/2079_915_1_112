package com.example.demo.repository;

import com.example.demo.entity.AuditTrailRecord;
import java.util.List;
import org.springframework.stereotype.Repository;
@Repository
public interface AuditTrailRecordRepository {

    AuditTrailRecord save(AuditTrailRecord record);

    List<AuditTrailRecord> findByCredentialId(Long credentialId);
}
