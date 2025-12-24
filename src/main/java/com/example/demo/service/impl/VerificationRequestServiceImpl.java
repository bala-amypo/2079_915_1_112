package com.example.demo.service.impl;

import com.example.demo.entity.AuditTrailRecord;
import com.example.demo.entity.CredentialRecord;
import com.example.demo.entity.VerificationRequest;
import com.example.demo.repository.VerificationRequestRepository;
import com.example.demo.service.AuditTrailService;
import com.example.demo.service.CredentialRecordService;
import com.example.demo.service.VerificationRequestService;
import com.example.demo.service.VerificationRuleService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VerificationRequestServiceImpl
        implements VerificationRequestService {

    private final VerificationRequestRepository requestRepository;
    private final CredentialRecordService credentialService;
    private final VerificationRuleService ruleService;
    private final AuditTrailService auditTrailService;

    // ✅ CONSTRUCTOR EXACTLY AS TEST EXPECTS
    public VerificationRequestServiceImpl(
            VerificationRequestRepository requestRepository,
            CredentialRecordService credentialService,
            VerificationRuleService ruleService,
            AuditTrailService auditTrailService
    ) {
        this.requestRepository = requestRepository;
        this.credentialService = credentialService;
        this.ruleService = ruleService;
        this.auditTrailService = auditTrailService;
    }

    @Override
    public VerificationRequest createRequest(VerificationRequest request) {
        request.setRequestedAt(LocalDateTime.now());
        return requestRepository.save(request);
    }

    @Override
    public VerificationRequest processVerification(Long requestId) {

        VerificationRequest request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        CredentialRecord credential =
                credentialService.getCredentialById(request.getCredentialId());

        boolean valid = ruleService.validateCredential(credential);

        request.setStatus(valid ? "APPROVED" : "REJECTED");
        requestRepository.save(request);

        AuditTrailRecord audit = new AuditTrailRecord();
        audit.setAction("VERIFICATION_" + request.getStatus());
        audit.setTimestamp(LocalDateTime.now());
        auditTrailService.save(audit);

        return request;
    }

    @Override
    public List<VerificationRequest> getRequestsByCredential(Long credentialId) {
        return requestRepository.findByCredentialId(credentialId);
    }
}
