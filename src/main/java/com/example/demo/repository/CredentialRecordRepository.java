package com.example.demo.repository;

import java.time.LocalDate;
import java.util.*;
import com.example.demo.entity.*;

public interface CredentialRecordRepository {
    CredentialRecord save(CredentialRecord r);
    Optional<CredentialRecord> findById(Long id);
    List<CredentialRecord> findByHolderId(Long holderId);
    Optional<CredentialRecord> findByCredentialCode(String code);
    List<CredentialRecord> findExpiredBefore(LocalDate date);
    List<CredentialRecord> findByStatusUsingHql(String status);
    List<CredentialRecord> searchByIssuerAndType(String issuer, String type);
    List<CredentialRecord> findAll();
}
