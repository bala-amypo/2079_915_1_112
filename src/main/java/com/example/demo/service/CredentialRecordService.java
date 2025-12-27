package com.example.demo.service;

import com.example.demo.entity.CredentialRecord;

import java.util.List;

public interface CredentialRecordService {

    CredentialRecord create(CredentialRecord record);

    CredentialRecord update(Long id, CredentialRecord record);

    List<CredentialRecord> getCredentialsByHolder(Long holderId);

    CredentialRecord getByCode(String code);

    CredentialRecord getById(Long id);
}
