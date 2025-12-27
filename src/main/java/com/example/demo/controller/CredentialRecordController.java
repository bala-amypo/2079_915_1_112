package com.example.demo.controller;

import com.example.demo.entity.CredentialRecord;
import com.example.demo.service.CredentialRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/credentials")
public class CredentialRecordController {

    private final CredentialRecordService service;

    public CredentialRecordController(CredentialRecordService service) {
        this.service = service;
    }

    // ================= CREATE CREDENTIAL =================
    @PostMapping
    public ResponseEntity<CredentialRecord> create(
            @RequestBody CredentialRecord record) {
        return ResponseEntity.ok(service.createCredential(record));
    }

    // ================= UPDATE CREDENTIAL =================
    @PutMapping("/{id}")
    public ResponseEntity<CredentialRecord> update(
            @PathVariable Long id,
            @RequestBody CredentialRecord update) {
        return ResponseEntity.ok(service.updateCredential(id, update));
    }

    // ================= GET BY HOLDER =================
    @GetMapping("/holder/{holderId}")
    public ResponseEntity<List<CredentialRecord>> getByHolder(
            @PathVariable Long holderId) {
        return ResponseEntity.ok(service.getCredentialsByHolder(holderId));
    }

    // ================= GET BY CODE =================
    @GetMapping("/code/{code}")
    public ResponseEntity<CredentialRecord> getByCode(
            @PathVariable String code) {
        return ResponseEntity.ok(service.getCredentialByCode(code));
    }
}
