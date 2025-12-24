package com.example.demo.repository;

import com.example.demo.entity.CredentialRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CredentialRecordRepository
        extends JpaRepository<CredentialRecord, Long> {

    Optional<CredentialRecord> findByCredentialCode(String credentialCode);

    List<CredentialRecord> findByHolderId(Long holderId);

    // ✅ REQUIRED BY TEST CASES
    List<CredentialRecord> findExpiredBefore(LocalDate date);

    // ✅ REQUIRED BY TEST CASES
    @Query("SELECT c FROM CredentialRecord c WHERE c.status = :status")
    List<CredentialRecord> findByStatusUsingHql(@Param("status") String status);

    // ✅ REQUIRED BY TEST CASES
    @Query("""
        SELECT c FROM CredentialRecord c
        WHERE c.issuer = :issuer AND c.type = :type
    """)
    List<CredentialRecord> searchByIssuerAndType(
            @Param("issuer") String issuer,
            @Param("type") String type
    );
}
