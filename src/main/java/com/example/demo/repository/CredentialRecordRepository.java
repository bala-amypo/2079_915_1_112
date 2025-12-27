package com.example.demo.repository;

import com.example.demo.entity.CredentialRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CredentialRecordRepository extends JpaRepository<CredentialRecord, Long> {

    CredentialRecord findByCredentialCode(String credentialCode);

    // ✅ alias for service compatibility
    default CredentialRecord findByCode(String code) {
        return findByCredentialCode(code);
    }

    List<CredentialRecord> findByHolderId(Long holderId);

    List<CredentialRecord> findByExpiryDateBefore(LocalDate date);
}
