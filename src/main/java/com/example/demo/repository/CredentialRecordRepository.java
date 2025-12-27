package com.example.demo.repository;

import com.example.demo.entity.CredentialRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CredentialRecordRepository extends JpaRepository<CredentialRecord, Long> {

    CredentialRecord findByCredentialCode(String credentialCode);

    List<CredentialRecord> findByExpiryDateBefore(LocalDate date);

    @Query("SELECT c FROM CredentialRecord c WHERE c.status = :status")
    List<CredentialRecord> findByStatusUsingHql(@Param("status") String status);

    @Query("SELECT c FROM CredentialRecord c WHERE c.issuer = :issuer AND c.credentialType = :type")
    List<CredentialRecord> searchByIssuerAndType(
            @Param("issuer") String issuer,
            @Param("type") String type
    );
}
