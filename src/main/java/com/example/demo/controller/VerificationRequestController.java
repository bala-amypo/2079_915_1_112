package com.example.demo.controller;

import com.example.demo.entity.VerificationRequest;
import com.example.demo.service.VerificationRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/verifications")
public class VerificationRequestController {

    private final VerificationRequestService service;

    public VerificationRequestController(
            VerificationRequestService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<VerificationRequest> initiate(
            @RequestBody VerificationRequest request) {
        return ResponseEntity.ok(service.initiateVerification(request));
    }

    @PostMapping("/{id}/process")
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
}
