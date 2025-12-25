package com.example.demo.repository;

import com.example.demo.entity.CredentialRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CredentialRecordRepository extends JpaRepository<CredentialRecord, Long> {

    // ✔ Used by tests
    Optional<CredentialRecord> findByCredentialCode(String credentialCode);

    // ✔ Used by tests (expiryDate exists in entity)
    List<CredentialRecord> findByExpiryDateBefore(LocalDate date);

    // ✔ Used by tests (JPQL)
    @Query("SELECT c FROM CredentialRecord c WHERE c.status = :status")
    List<CredentialRecord> findByStatusUsingHql(String status);

    // ✔ Used by tests
    @Query("""
        SELECT c FROM CredentialRecord c
        WHERE c.issuer = :issuer AND c.credentialType = :type
    """)
    List<CredentialRecord> searchByIssuerAndType(String issuer, String type);
}
