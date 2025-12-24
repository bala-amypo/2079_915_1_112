package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.CredentialRecord;

public interface CredentialRecordRepository
        extends JpaRepository<CredentialRecord, Long> {

    Optional<CredentialRecord> findByCredentialCode(String credentialCode);

    List<CredentialRecord> findByHolderId(Long holderId);

    // ✅ FIXED METHOD NAME
    List<CredentialRecord> findByExpiryDateBefore(LocalDate date);

    // OPTIONAL (if used in tests)
    @Query("SELECT c FROM CredentialRecord c WHERE c.status = :status")
    List<CredentialRecord> findByStatusUsingHql(String status);

    // OPTIONAL (if used in tests)
    @Query("SELECT c FROM CredentialRecord c WHERE c.issuer = :issuer AND c.credentialName = :type")
    List<CredentialRecord> searchByIssuerAndType(String issuer, String type);
}
