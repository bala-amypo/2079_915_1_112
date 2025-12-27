package com.example.demo.repository;

import com.example.demo.entity.CredentialRecord;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
@Repository
public interface CredentialRecordRepository {

    CredentialRecord save(CredentialRecord record);

    Optional<CredentialRecord> findById(Long id);

    List<CredentialRecord> findByHolderId(Long holderId);

    Optional<CredentialRecord> findByCredentialCode(String credentialCode);

    List<CredentialRecord> findExpiredBefore(LocalDate date);

    List<CredentialRecord> findByStatusUsingHql(String status);

    List<CredentialRecord> searchByIssuerAndType(String issuer, String credentialType);

    List<CredentialRecord> findAll();
}
