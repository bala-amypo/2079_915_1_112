package com.example.demo.service;

import com.example.demo.entity.CredentialRecord;
import java.util.List;

public interface CredentialRecordService {

    CredentialRecord createCredential(CredentialRecord credential);

    CredentialRecord getCredentialById(Long id);

    List<CredentialRecord> getAllCredentials();

    List<CredentialRecord> getCredentialsByHolder(Long holderId);

    CredentialRecord updateCredential(Long id, CredentialRecord credential);

    void deleteCredential(Long id);
}
