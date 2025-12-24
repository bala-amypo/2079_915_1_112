package com.example.demo.controller;

import com.example.demo.entity.AuditTrailRecord;
import com.example.demo.service.AuditTrailService;
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
    public void log(@RequestBody AuditTrailRecord record) {
        service.logEvent(record);
    }

    @GetMapping("/{credentialId}")
    public List<AuditTrailRecord> getByCredential(@PathVariable Long credentialId) {
        return service.getLogsByCredential(credentialId);
    }
}
