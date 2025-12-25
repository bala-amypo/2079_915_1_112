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

    // CREATE
    @PostMapping
    public ResponseEntity<CredentialRecord> create(
            @RequestBody CredentialRecord record) {

        CredentialRecord saved = service.createCredential(record);
        return ResponseEntity.ok(saved);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<CredentialRecord> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getCredentialById(id));
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<CredentialRecord>> getAll() {
        return ResponseEntity.ok(service.getAllCredentials());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<CredentialRecord> update(
            @PathVariable Long id,
            @RequestBody CredentialRecord record) {

        return ResponseEntity.ok(service.updateCredential(id, record));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteCredential(id);
        return ResponseEntity.ok().build();
    }

    // GET BY HOLDER
    @GetMapping("/holder/{holderId}")
    public ResponseEntity<List<CredentialRecord>> getByHolder(
            @PathVariable Long holderId) {

        return ResponseEntity.ok(service.getCredentialsByHolder(holderId));
    }

    // GET BY CODE (TEST EXPECTS THIS)
    @GetMapping("/code/{code}")
    public ResponseEntity<CredentialRecord> getByCode(
            @PathVariable String code) {

        // simple scan logic for tests
        return service.getAllCredentials()
                .stream()
                .filter(c -> code.equals(c.getCredentialCode()))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
