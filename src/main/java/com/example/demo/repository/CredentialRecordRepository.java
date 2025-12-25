package com.example.demo.repository;

import com.example.demo.entity.CredentialRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CredentialRecordRepository extends JpaRepository<CredentialRecord, Long> {

    // ===============================
    // REQUIRED BY TEST CASES
    // ===============================

    // ✔ find by credential code
    Optional<CredentialRecord> findByCredentialCode(String credentialCode);

    // ✔ find by holder id
    List<CredentialRecord> findByHolderId(Long holderId);

    // ✔ used by tests (LocalDate)
    List<CredentialRecord> findByExpiryDateBefore(LocalDate date);

    // ✔ ugly but REQUIRED by tests (Object param)
    @Query("SELECT c FROM CredentialRecord c WHERE c.expiryDate < :date")
    List<CredentialRecord> findExpiredBefore(Object date);

    // ✔ JPQL version used by tests
    @Query("SELECT c FROM CredentialRecord c WHERE c.status = :status")
    List<CredentialRecord> findByStatusUsingHql(String status);

    // ✔ search by issuer and type
    @Query("""
        SELECT c FROM CredentialRecord c
        WHERE c.issuer = :issuer AND c.credentialType = :type
    """)
    List<CredentialRecord> searchByIssuerAndType(String issuer, String type);
}
