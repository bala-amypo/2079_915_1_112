package com.example.demo.service.impl;

import com.example.demo.entity.AuditTrailRecord;
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
public class VerificationRequestServiceImpl implements VerificationRequestService {

    private final VerificationRequestRepository requestRepo;
    private final CredentialRecordService credentialService;
    private final VerificationRuleService ruleService;
    private final AuditTrailService auditService;

    public VerificationRequestServiceImpl(
            VerificationRequestRepository requestRepo,
            CredentialRecordService credentialService,
            VerificationRuleService ruleService,
            AuditTrailService auditService
    ) {
        this.requestRepo = requestRepo;
        this.credentialService = credentialService;
        this.ruleService = ruleService;
        this.auditService = auditService;
    }

    @Override
    public VerificationRequest initiateVerification(VerificationRequest request) {
        request.setRequestedAt(LocalDateTime.now());
        VerificationRequest saved = requestRepo.save(request);

        AuditTrailRecord log = new AuditTrailRecord();
        log.setCredentialId(request.getCredentialId());
        log.setAction("VERIFICATION_REQUESTED");
        log.setLoggedAt(LocalDateTime.now());
        auditService.save(log);

        return saved;
    }

    // ✅ THIS METHOD WAS MISSING
    @Override
    public VerificationRequest processVerification(Long requestId) {

        VerificationRequest request =
                requestRepo.findById(requestId).orElse(null);

        if (request == null) {
            return null;
        }

        // simple rule validation hook
        ruleService.validateCredential(
                credentialService.getCredentialById(request.getCredentialId())
        );

        AuditTrailRecord log = new AuditTrailRecord();
        log.setCredentialId(request.getCredentialId());
        log.setAction("VERIFICATION_PROCESSED");
        log.setLoggedAt(LocalDateTime.now());
        auditService.save(log);

        return request;
    }

    @Override
    public List<VerificationRequest> getRequestsByCredential(Long credentialId) {
        return requestRepo.findByCredentialId(credentialId);
    }
}
