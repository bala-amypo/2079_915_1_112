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
    public AuditTrailRecord create(@RequestBody AuditTrailRecord record) {
        return service.save(record);
    }

    @GetMapping
    public List<AuditTrailRecord> getAll() {
        return service.getAll();
    }
}
