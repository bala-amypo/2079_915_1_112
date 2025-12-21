package com.example.demo.controller;

import com.example.demo.entity.CredentialRecord;
import com.example.demo.service.CredentialRecordService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/credentials")
public class CredentialRecordController {

    private final CredentialRecordService service;

    public CredentialRecordController(
            CredentialRecordService service) {
        this.service = service;
    }

    // POST / - Create credential
    @PostMapping
    public CredentialRecord create(
            @Valid @RequestBody CredentialRecord record) {
        return service.createCredential(record);
    }

    // PUT /{id} - Update credential
    @PutMapping("/{id}")
    public CredentialRecord update(
            @PathVariable Long id,
            @Valid @RequestBody CredentialRecord record) {
        return service.updateCredential(id, record);
    }

    // GET /holder/{holderId}
    @GetMapping("/holder/{holderId}")
    public List<CredentialRecord> getByHolder(
            @PathVariable Long holderId) {
        return service.getByUserId(holderId);
    }

    // GET /code/{credentialCode}
    @GetMapping("/code/{credentialCode}")
    public CredentialRecord getByCode(
            @PathVariable String credentialCode) {
        return service.getByCredentialCode(credentialCode);
    }

    // GET / - List all credentials
    @GetMapping
    public List<CredentialRecord> getAll() {
        return service.getAllCredentials();
    }
}
