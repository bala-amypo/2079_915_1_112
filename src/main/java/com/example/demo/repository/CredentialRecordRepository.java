package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.CredentialRecord;

@Repository
public interface CredentialRecordRepository extends JpaRepository<CredentialRecord, Long> {

    // Find credential using credential code
    Optional<CredentialRecord> findByCredentialCode(String credentialCode);

    // Find all credentials belonging to a holder/user
    List<CredentialRecord> findByHolderId(Long holderId);
}
