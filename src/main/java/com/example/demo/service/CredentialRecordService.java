package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.CredentialRecord;

public interface CredentialRecordService {

    CredentialRecord createCredential(CredentialRecord record);

    CredentialRecord getCredentialById(Long id);

    CredentialRecord getCredentialByCode(String code);

    List<CredentialRecord> getCredentialsByHolder(Long holderId);

    List<CredentialRecord> getAllCredentials();

    CredentialRecord updateCredential(Long id, CredentialRecord record);

    void deleteCredential(Long id);
}
