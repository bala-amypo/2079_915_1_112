package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.AuditTrailRecord;
import com.example.demo.service.AuditTrailService;

@RestController
@RequestMapping("/audit")
public class AuditTrailController {

    private final AuditTrailService service;

    public AuditTrailController(
            AuditTrailService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AuditTrailRecord> log(
            @RequestBody AuditTrailRecord record) {
        return ResponseEntity.ok(service.logEvent(record));
    }

    @GetMapping("/{credentialId}")
    public ResponseEntity<List<AuditTrailRecord>> getByCredential(
            @PathVariable Long credentialId) {
        return ResponseEntity.ok(service.getLogsByCredential(credentialId));
    }
}
