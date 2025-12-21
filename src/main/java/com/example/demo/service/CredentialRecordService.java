package com.example.demo.service;

import com.example.demo.entity.CredentialRecord;

import java.util.List;

public interface CredentialRecordService {

    CredentialRecord createCredential(CredentialRecord credential);

    CredentialRecord updateCredential(Long id, CredentialRecord credential);

    List<CredentialRecord> getByUserId(Long userId);

    CredentialRecord getByCredentialCode(String credentialCode);

    List<CredentialRecord> getAllCredentials();
}
