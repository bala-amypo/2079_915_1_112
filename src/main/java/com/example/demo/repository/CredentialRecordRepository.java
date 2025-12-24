package com.example.demo.repository;

import com.example.demo.entity.CredentialRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CredentialRecordRepository extends JpaRepository<CredentialRecord, Long> {

    List<CredentialRecord> findByHolderId(long holderId);

    List<CredentialRecord> findExpiredBefore(LocalDate date);

    @Query("select c from CredentialRecord c where c.status = :status")
    List<CredentialRecord> findByStatusUsingHql(@Param("status") String status);

    @Query("select c from CredentialRecord c where c.issuer = :issuer and c.type = :type")
    List<CredentialRecord> searchByIssuerAndType(
            @Param("issuer") String issuer,
            @Param("type") String type);
}
