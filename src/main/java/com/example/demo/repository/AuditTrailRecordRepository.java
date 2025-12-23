package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.AuditTrailRecord;

public interface AuditTrailRecordRepository
        extends JpaRepository<AuditTrailRecord, Long> {

    List<AuditTrailRecord> findByCredentialId(Long credentialId);
}
