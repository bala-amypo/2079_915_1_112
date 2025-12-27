package com.example.demo.controller;

import com.example.demo.entity.AuditTrailRecord;
import com.example.demo.service.AuditTrailService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> log(@RequestBody AuditTrailRecord record) {
        service.logEvent(record); // ✅ NO return value
        return ResponseEntity.ok("Audit logged successfully");
    }

    @GetMapping("/credential/{credentialId}")
    public ResponseEntity<List<AuditTrailRecord>> getByCredential(
            @PathVariable Long credentialId) {

        return ResponseEntity.ok(
                service.getLogsByCredential(credentialId)
        );
    }
}
