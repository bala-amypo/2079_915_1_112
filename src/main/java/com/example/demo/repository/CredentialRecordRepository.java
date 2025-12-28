package com.example.demo.repository;

import com.example.demo.entity.CredentialRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CredentialRecordRepository
        extends JpaRepository<CredentialRecord, Long> {

    // ===============================
    // BASIC QUERIES
    // ===============================
    List<CredentialRecord> findByHolderId(Long holderId);

    Optional<CredentialRecord> findByCredentialCode(String credentialCode);

    // ===============================
    // 🔥 REQUIRED BY TESTS (DO NOT RENAME)
    // ===============================
    List<CredentialRecord> findExpiredBefore(LocalDate date);

    // (Optional but safe to keep)
    List<CredentialRecord> findByExpiryDateBefore(LocalDate date);

    // ===============================
    // HQL / JPQL QUERIES
    // ===============================
    @Query("SELECT c FROM CredentialRecord c WHERE c.status = :status")
    List<CredentialRecord> findByStatusUsingHql(String status);

    @Query("""
           SELECT c FROM CredentialRecord c
           WHERE c.issuer = :issuer
             AND c.credentialType = :credentialType
           """)
    List<CredentialRecord> searchByIssuerAndType(
            String issuer,
            String credentialType
    );
}
