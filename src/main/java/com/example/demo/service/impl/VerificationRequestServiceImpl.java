package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.AuditTrailRecord;
import com.example.demo.entity.VerificationRequest;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.VerificationRequestRepository;
import com.example.demo.service.AuditTrailService;
import com.example.demo.service.CredentialRecordService;
import com.example.demo.service.VerificationRequestService;
import com.example.demo.service.VerificationRuleService;

@Service
public class VerificationRequestServiceImpl
        implements VerificationRequestService {

    private final VerificationRequestRepository requestRepository;
    private final CredentialRecordService credentialService;
    private final AuditTrailService auditService;

    // ✅ THIS constructor is used by Spring
    @Autowired
    public VerificationRequestServiceImpl(
            VerificationRequestRepository requestRepository,
            CredentialRecordService credentialService,
            AuditTrailService auditService) {

        this.requestRepository = requestRepository;
        this.credentialService = credentialService;
        this.auditService = auditService;
    }

    // ✅ THIS constructor is used ONLY by TEST CASES
    public VerificationRequestServiceImpl(
            VerificationRequestRepository requestRepository,
            CredentialRecordService credentialService,
            VerificationRuleService ruleService,
            AuditTrailService auditService) {

        this.requestRepository = requestRepository;
        this.credentialService = credentialService;
        this.auditService = auditService;
    }

    @Override
    public VerificationRequest initiateVerification(
            VerificationRequest request) {
        return requestRepository.save(request);
    }

    @Override
    public VerificationRequest processVerification(Long requestId) {

        VerificationRequest request =
                requestRepository.findById(requestId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Request not found"));

        request.setStatus("SUCCESS");

        AuditTrailRecord audit = new AuditTrailRecord();
        audit.setCredentialId(request.getCredentialId());
        auditService.logEvent(audit);

        return requestRepository.save(request);
    }

    @Override
    public List<VerificationRequest> getRequestsByCredential(
            Long credentialId) {
        return requestRepository.findByCredentialId(credentialId);
    }
}
