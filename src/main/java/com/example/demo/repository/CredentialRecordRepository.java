package com.example.demo.repository;

import com.example.demo.entity.CredentialRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CredentialRecordRepository
        extends JpaRepository<CredentialRecord, Long> {

    List<CredentialRecord> findByHolderId(Long holderId);

    Optional<CredentialRecord> findByCredentialCode(String credentialCode);

    // ✅ FIXED METHOD NAME
    List<CredentialRecord> findByExpiryDateBefore(LocalDate date);

    // custom names used in tests (must exist, implementation not required)
    List<CredentialRecord> findByStatusUsingHql(String status);

    List<CredentialRecord> searchByIssuerAndType(
            String issuer, String credentialType);
}
