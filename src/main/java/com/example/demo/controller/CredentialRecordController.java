package com.example.demo.controller;

import com.example.demo.entity.CredentialRecord;
import com.example.demo.service.CredentialRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/credentials")
public class CredentialRecordController {

    private final CredentialRecordService service;

    public CredentialRecordController(CredentialRecordService service) {
        this.service = service;
    }

    @PostMapping
    public CredentialRecord create(@RequestBody CredentialRecord credential) {
        return service.createCredential(credential);
    }

    @GetMapping("/{id}")
    public CredentialRecord getById(@PathVariable Long id) {
        return service.getCredentialById(id);
    }

    @GetMapping
    public List<CredentialRecord> getAll() {
        return service.getAllCredentials();
    }

    @GetMapping("/code/{code}")
    public CredentialRecord getByCode(@PathVariable String code) {
        return service.getCredentialByCode(code);
    }
}
