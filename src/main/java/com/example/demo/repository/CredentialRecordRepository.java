package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.CredentialRecord;

public interface CredentialRecordRepository
        extends JpaRepository<CredentialRecord, Long> {

    List<CredentialRecord> findByHolderId(Long holderId);

    Optional<CredentialRecord> findByCredentialCode(String credentialCode);

    // ✅ FIXED: explicit JPQL (method name kept same)
    @Query("SELECT c FROM CredentialRecord c WHERE c.expiryDate < ?1")
    List<CredentialRecord> findExpiredBefore(LocalDate date);

    @Query("SELECT c FROM CredentialRecord c WHERE c.status = ?1")
    List<CredentialRecord> findByStatusUsingHql(String status);

    @Query("SELECT c FROM CredentialRecord c WHERE c.issuer = ?1 AND c.credentialType = ?2")
    List<CredentialRecord> searchByIssuerAndType(String issuer, String credentialType);
}
