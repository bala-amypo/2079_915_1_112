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

    @GetMapping("/holder/{holderId}")
    public List<CredentialRecord> getByHolder(@PathVariable Long holderId) {
        return service.getCredentialsByHolder(holderId);
    }

    @PutMapping("/{id}")
    public CredentialRecord update(
            @PathVariable Long id,
            @RequestBody CredentialRecord credential) {
        return service.updateCredential(id, credential);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteCredential(id);
    }
}
