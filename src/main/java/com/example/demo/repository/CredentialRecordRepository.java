package com.example.demo.repository;

import com.example.demo.entity.CredentialRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CredentialRecordRepository extends JpaRepository<CredentialRecord, Long> {

    // ---------- REQUIRED BY TESTS ----------

    Optional<List<CredentialRecord>> findByHolderId(Long holderId);

    Optional<CredentialRecord> findByCredentialCode(String credentialCode);

    // 🔥 KEEP THIS NAME — TESTS EXPECT IT
    @Query("SELECT c FROM CredentialRecord c WHERE c.expiryDate < :date")
    Optional<List<CredentialRecord>> findExpiredBefore(@Param("date") LocalDate date);

    // Used in HQL tests
    @Query("SELECT c FROM CredentialRecord c WHERE c.status = :status")
    List<CredentialRecord> findByStatusUsingHql(@Param("status") String status);

    // Used in search tests
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
