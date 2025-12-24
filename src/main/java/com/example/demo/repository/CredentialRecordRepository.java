package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.CredentialRecord;

@Repository
public interface CredentialRecordRepository extends JpaRepository<CredentialRecord, Long> {

    // ✅ FIXED METHOD NAME (matches entity field: expiryDate)
    List<CredentialRecord> findExpiryDateBefore(LocalDate date);

    // keep all other existing methods exactly as they are
}
