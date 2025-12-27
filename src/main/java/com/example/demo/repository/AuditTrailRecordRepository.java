package com.example.demo.repository;

import com.example.demo.entity.AuditTrailRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditTrailRecordRepository
        extends JpaRepository<AuditTrailRecord, Long> {

    List<AuditTrailRecord> findByCredentialId(Long credentialId);
}
