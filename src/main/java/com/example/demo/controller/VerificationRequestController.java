package com.example.demo.controller;

import com.example.demo.entity.VerificationRequest;
import com.example.demo.service.VerificationRequestService;
import org.springframework.http.ResponseEntity;

public class VerificationRequestController {

    private final VerificationRequestService service;

    public VerificationRequestController(
            VerificationRequestService service) {
        this.service = service;
    }

    public ResponseEntity<VerificationRequest> initiate(
            VerificationRequest request) {
        return ResponseEntity.ok(service.initiateVerification(request));
    }
}
