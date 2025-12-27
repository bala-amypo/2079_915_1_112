package com.example.demo.repository;

import com.example.demo.entity.AuditTrailRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditTrailRecordRepository extends JpaRepository<AuditTrailRecord, Long> {
}
