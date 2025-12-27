package com.example.demo.service;

import com.example.demo.entity.CredentialRecord;

import java.time.LocalDate;
import java.util.List;

public interface CredentialRecordService {

    CredentialRecord createCredential(CredentialRecord record);

    CredentialRecord updateCredential(Long id, CredentialRecord record);

    CredentialRecord getById(Long id);

    List<CredentialRecord> getByHolderId(Long holderId);

    CredentialRecord getByCode(String code);

    List<CredentialRecord> findExpired(LocalDate date);
}
