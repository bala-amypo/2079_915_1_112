package com.example.demo.controller;

import com.example.demo.entity.CredentialHolderProfile;
import com.example.demo.service.CredentialHolderProfileService;
import org.springframework.http.ResponseEntity;

public class CredentialHolderController {

    private final CredentialHolderProfileService service;

    public CredentialHolderController(CredentialHolderProfileService service) {
        this.service = service;
    }

    public ResponseEntity<CredentialHolderProfile> create(
            CredentialHolderProfile profile) {
        return ResponseEntity.ok(service.createHolder(profile));
    }

    public ResponseEntity<CredentialHolderProfile> getById(Long id) {
        return ResponseEntity.ok(service.getHolderById(id));
    }

    public ResponseEntity<CredentialHolderProfile> updateStatus(
            Long id, boolean active) {
        return ResponseEntity.ok(service.updateStatus(id, active));
    }
}
