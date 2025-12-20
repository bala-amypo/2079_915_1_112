package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.CredentialRecord;

public interface CredentialRecordRepository
        extends JpaRepository<CredentialRecord, Long> {

    List<CredentialRecord> findByUser_Id(Long userId);
}
