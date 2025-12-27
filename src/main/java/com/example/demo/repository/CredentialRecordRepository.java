package com.example.demo.repository;

import com.example.demo.entity.CredentialRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CredentialRecordRepository
        extends JpaRepository<CredentialRecord, Long> {

    Optional<CredentialRecord> findByCredentialCode(String credentialCode);

    List<CredentialRecord> findByHolderId(Long holderId);

    // ✅ Correct derived query
    List<CredentialRecord> findByExpiryDateBefore(LocalDate date);

    // ✅ FIXED: Explicit JPQL (HQL) query
    @Query("SELECT c FROM CredentialRecord c WHERE c.status = :status")
    List<CredentialRecord> findByStatusUsingHql(@Param("status") String status);

    // ✅ FIXED: Explicit JPQL query
    @Query("SELECT c FROM CredentialRecord c WHERE c.issuer = :issuer AND c.credentialType = :type")
    List<CredentialRecord> searchByIssuerAndType(
            @Param("issuer") String issuer,
            @Param("type") String type);
}
