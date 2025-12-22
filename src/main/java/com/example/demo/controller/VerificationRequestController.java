package com.example.demo.controller;

import com.example.demo.entity.VerificationRequest;
import com.example.demo.service.VerificationRequestService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/verification")
public class VerificationRequestController {

    private final VerificationRequestService service;

    public VerificationRequestController(VerificationRequestService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<VerificationRequest> initiate(
            @Valid @RequestBody VerificationRequest request) {
        return ResponseEntity.ok(service.initiateVerification(request));
    }

    @PutMapping("/{id}/process")
    public ResponseEntity<VerificationRequest> process(
            @PathVariable Long id) {
        return ResponseEntity.ok(service.processVerification(id));
    }

    @GetMapping("/credential/{credentialId}")
    public ResponseEntity<List<VerificationRequest>> getByCredential(
            @PathVariable Long credentialId) {
        return ResponseEntity.ok(
                service.getRequestsByCredential(credentialId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VerificationRequest> getById(
            @PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<VerificationRequest>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
}
