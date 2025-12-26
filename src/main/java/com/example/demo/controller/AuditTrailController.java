package com.example.demo.controller;

import com.example.demo.entity.AuditTrailRecord;
import com.example.demo.service.AuditTrailService;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class AuditTrailController {

    private final AuditTrailService service;

    public AuditTrailController(AuditTrailService service) {
        this.service = service;
    }

    public ResponseEntity<AuditTrailRecord> log(
            AuditTrailRecord record) {
        return ResponseEntity.ok(service.logEvent(record));
    }

    public ResponseEntity<List<AuditTrailRecord>> getByCredential(
            Long credentialId) {
        return ResponseEntity.ok(
                service.getLogsByCredential(credentialId));
    }
}
