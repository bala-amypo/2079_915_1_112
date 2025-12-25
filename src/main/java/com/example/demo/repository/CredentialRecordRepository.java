package com.example.demo.repository;

import com.example.demo.entity.CredentialRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CredentialRecordRepository extends JpaRepository<CredentialRecord, Long> {

    // Used in tests
    Optional<List<CredentialRecord>> findByHolderId(Long holderId);

    // Used in tests
    Optional<CredentialRecord> findByCredentialCode(String credentialCode);

    // ✅ FIXED: matches entity field "expiryDate"
    List<CredentialRecord> findByExpiryDateBefore(LocalDate date);

    // Used in tests
    @Query("SELECT c FROM CredentialRecord c WHERE c.status = :status")
    List<CredentialRecord> findByStatusUsingHql(@Param("status") String status);

    // Used in tests
    @Query("""
           SELECT c FROM CredentialRecord c
           WHERE c.issuer = :issuer
           AND c.metadataJson LIKE %:type%
           """)
    List<CredentialRecord> searchByIssuerAndType(
            @Param("issuer") String issuer,
            @Param("type") String type
    );
}
