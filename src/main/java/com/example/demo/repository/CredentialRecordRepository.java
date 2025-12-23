package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.CredentialRecord;

public interface CredentialRecordRepository extends JpaRepository<CredentialRecord, Long> {

    // ✅ REQUIRED BY SERVICE
    Optional<CredentialRecord> findByCredentialCode(String credentialCode);

    // ✅ REQUIRED BY SERVICE
    List<CredentialRecord> findByHolderId(Long holderId);

    // ✅ REQUIRED BY TESTS
    List<CredentialRecord> findExpiredBefore(LocalDate date);

    // ✅ REQUIRED BY TESTS
    @Query("SELECT c FROM CredentialRecord c WHERE c.status = :status")
    List<CredentialRecord> findByStatusUsingHql(String status);

    // ✅ REQUIRED BY TESTS
    @Query("SELECT c FROM CredentialRecord c WHERE c.issuer = :issuer AND c.credentialType = :type")
    List<CredentialRecord> searchByIssuerAndType(String issuer, String type);
}
