package com.example.demo.controller;

import com.example.demo.entity.AuditTrailRecord;
import com.example.demo.service.AuditTrailService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/audit")
public class AuditTrailController {

    private final AuditTrailService service;

    public AuditTrailController(AuditTrailService service) {
        this.service = service;
    }

    @PostMapping
    public AuditTrailRecord logEvent(
            @Valid @RequestBody AuditTrailRecord record) {
        return service.logEvent(record);
    }

    @GetMapping("/credential/{credentialId}")
    public List<AuditTrailRecord> getByCredential(
            @PathVariable Long credentialId) {
        return service.getByCredentialId(credentialId);
    }

    @GetMapping
    public List<AuditTrailRecord> getAll() {
        return service.getAll();
    }
}
