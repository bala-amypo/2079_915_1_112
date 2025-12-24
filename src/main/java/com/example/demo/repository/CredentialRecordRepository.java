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

    List<CredentialRecord> findByExpiryDateBefore(LocalDate date);

    @Query("SELECT c FROM CredentialRecord c WHERE c.status = :status")
    List<CredentialRecord> findByStatusUsingHql(String status);

    // ✅ FIXED FIELD NAME
    @Query("SELECT c FROM CredentialRecord c WHERE c.issuer = :issuer AND c.credentialCode = :type")
    List<CredentialRecord> searchByIssuerAndType(String issuer, String type);
}
