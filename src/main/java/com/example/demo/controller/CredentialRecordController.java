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
    public CredentialRecord create(@RequestBody CredentialRecord record) {
        return service.createCredential(record);
    }

    @GetMapping("/{id}")
    public CredentialRecord get(@PathVariable Long id) {
        return service.getCredentialById(id);
    }

    @GetMapping
    public List<CredentialRecord> getAll() {
        return service.getAllCredentials();
    }

    @PutMapping("/{id}")
    public CredentialRecord update(@PathVariable Long id, @RequestBody CredentialRecord record) {
        return service.updateCredential(id, record);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteCredential(id);
    }

    @GetMapping("/holder/{holderId}")
    public List<CredentialRecord> getByHolder(@PathVariable Long holderId) {
        return service.getCredentialsByHolder(holderId);
    }

    @GetMapping("/code/{code}")
    public List<CredentialRecord> getByCode(@PathVariable String code) {
        return service.getAllCredentials()
                .stream()
                .filter(c -> code.equals(c.getCredentialCode()))
                .toList();
    }
}
