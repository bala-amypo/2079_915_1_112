package com.example.demo.service.impl;

import com.example.demo.entity.AuditTrailRecord;
import com.example.demo.entity.CredentialRecord;
import com.example.demo.entity.VerificationRequest;
import com.example.demo.entity.VerificationRule;
import com.example.demo.repository.VerificationRequestRepository;
import com.example.demo.service.AuditTrailService;
import com.example.demo.service.CredentialRecordService;
import com.example.demo.service.VerificationRequestService;
import com.example.demo.service.VerificationRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VerificationRequestServiceImpl
        implements VerificationRequestService {

    private final VerificationRequestRepository repository;
    private final CredentialRecordService credentialService;
    private final VerificationRuleService ruleService;
    private final AuditTrailService auditService;

    public VerificationRequestServiceImpl(
            VerificationRequestRepository repository,
            CredentialRecordService credentialService,
            VerificationRuleService ruleService,
            AuditTrailService auditService) {

        this.repository = repository;
        this.credentialService = credentialService;
        this.ruleService = ruleService;
        this.auditService = auditService;
    }
    @Override
    public List<VerificationRequest> getRequestsByCredential(Long credentialId) {
    return repository.findByCredentialId(credentialId);
}

    @Override
public VerificationRequest initiateVerification(VerificationRequest request) {

    CredentialRecord credential =
            credentialService.getById(
                    request.getCredentialRecord().getId());

    VerificationRequest saved = repository.save(request);

    auditService.logEvent(
            new AuditTrailRecord(
                    "VERIFICATION_STARTED",
                    credential.getId())
    );

    return saved;
}

    @Override
    public VerificationRequest initiateVerification(
            VerificationRequest request) {

        CredentialRecord credential =
                credentialService.getById(
                        request.getCredential().getId());

        List<VerificationRule> rules =
                ruleService.getAllRules();

        VerificationRequest saved =
                repository.save(request);

        auditService.logEvent(
                new AuditTrailRecord(
                        "VERIFICATION_STARTED",
                        credential.getId())
        );

        return saved;
    }
}
