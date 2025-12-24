package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.AuditTrailRecord;

@Repository
public interface AuditTrailRecordRepository extends JpaRepository<AuditTrailRecord, Long> {

    // ✅ correct: credential is an object, id is inside it
    List<AuditTrailRecord> findByCredential_Id(Long credentialId);
}
