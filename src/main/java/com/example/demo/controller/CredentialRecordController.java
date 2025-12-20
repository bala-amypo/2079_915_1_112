package com.example.demo.controller;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.CredentialRecord;
import com.example.demo.service.CredentialRecordService;

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
}
