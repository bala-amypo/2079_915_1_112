package com.example.demo.repository;

import com.example.demo.entity.CredentialRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CredentialRecordRepository extends JpaRepository<CredentialRecord, Long> {

    // ---------------- REQUIRED BY TESTS ----------------

    List<CredentialRecord> findByHolderId(Long holderId);

    CredentialRecord findByCredentialCode(String credentialCode);

    // 🔥 KEEP NAME, RETURN List (Mockito expects this)
    @Query("SELECT c FROM CredentialRecord c WHERE c.expiryDate < :date")
    List<CredentialRecord> findExpiredBefore(@Param("date") LocalDate date);

    // Used by HQL tests
    @Query("SELECT c FROM CredentialRecord c WHERE c.status = :status")
    List<CredentialRecord> findByStatusUsingHql(@Param("status") String status);

    // Used by search tests
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
