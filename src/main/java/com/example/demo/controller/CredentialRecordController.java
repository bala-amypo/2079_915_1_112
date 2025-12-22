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

    @PostMapping
    public CredentialRecord create(
            @Valid @RequestBody CredentialRecord record) {
        return service.createCredential(record);
    }

    @PutMapping("/{id}")
    public CredentialRecord update(
            @PathVariable Long id,
            @Valid @RequestBody CredentialRecord record) {
        return service.updateCredential(id, record);
    }

    @GetMapping("/holder/{holderId}")
    public List<CredentialRecord> getByHolder(
            @PathVariable Long holderId) {
        return service.getByUserId(holderId);
    }

    @GetMapping("/code/{credentialCode}")
    public CredentialRecord getByCode(
            @PathVariable String credentialCode) {
        return service.getByCredentialCode(credentialCode);
    }

    @GetMapping
    public List<CredentialRecord> getAll() {
        return service.getAllCredentials();
    }
}
