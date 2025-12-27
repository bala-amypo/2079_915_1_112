package com.example.demo.controller;

import com.example.demo.entity.CredentialRecord;
import com.example.demo.service.CredentialRecordService;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class CredentialRecordController {

    private final CredentialRecordService service;

    public CredentialRecordController(CredentialRecordService service) {
        this.service = service;
    }

    public ResponseEntity<CredentialRecord> create(CredentialRecord record) {
        return ResponseEntity.ok(service.createCredential(record));
    }

    public ResponseEntity<CredentialRecord> update(
            Long id, CredentialRecord update) {
        return ResponseEntity.ok(service.updateCredential(id, update));
    }

    public ResponseEntity<List<CredentialRecord>> getByHolder(Long holderId) {
        return ResponseEntity.ok(service.getCredentialsByHolder(holderId));
    }

    public ResponseEntity<CredentialRecord> getByCode(String code) {
        return ResponseEntity.ok(service.getCredentialByCode(code));
    }
}
