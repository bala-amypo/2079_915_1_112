package com.example.demo.service.impl;

import com.example.demo.entity.VerificationRequest;
import com.example.demo.repository.VerificationRequestRepository;
import com.example.demo.service.VerificationRequestService;
import org.springframework.stereotype.Service;

@Service
public class VerificationRequestServiceImpl implements VerificationRequestService {

    private final VerificationRequestRepository repository;

    public VerificationRequestServiceImpl(VerificationRequestRepository repository) {
        this.repository = repository;
    }

    @Override
    public VerificationRequest initiateVerification(VerificationRequest request) {
        return repository.save(request);
    }

    @Override
    public VerificationRequest processVerification(Long id) {
        VerificationRequest req = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        return repository.save(req);
    }
}
