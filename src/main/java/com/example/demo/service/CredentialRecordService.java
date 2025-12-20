package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.CredentialRecord;

public interface CredentialRecordService {

    CredentialRecord createCredential(CredentialRecord credential);

    List<CredentialRecord> getByUserId(Long userId);
}
