package com.example.demo.service.impl;

import com.example.demo.entity.AuditTrailRecord;
import com.example.demo.entity.CredentialRecord;
import com.example.demo.entity.VerificationRequest;
import com.example.demo.repository.VerificationRequestRepository;
import com.example.demo.service.VerificationRequestService;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class VerificationRequestServiceImpl implements VerificationRequestService {

    private final VerificationRequestRepository requestRepo;
    private final CredentialRecordServiceImpl credentialService;
    private final VerificationRuleServiceImpl ruleService;
    private final AuditTrailServiceImpl auditService;

    public VerificationRequestServiceImpl(
            VerificationRequestRepository requestRepo,
            CredentialRecordServiceImpl credentialService,
            VerificationRuleServiceImpl ruleService,
            AuditTrailServiceImpl auditService) {

        this.requestRepo = requestRepo;
        this.credentialService = credentialService;
        this.ruleService = ruleService;
        this.auditService = auditService;
    }

    @Override
    public VerificationRequest initiateVerification(VerificationRequest request) {
        return requestRepo.save(request);
    }

    @Override
    public VerificationRequest processVerification(Long requestId) {

        VerificationRequest req =
                requestRepo.findById(requestId).orElseThrow();

        CredentialRecord credential = credentialService.findAll()
                .stream()
                .filter(c -> c.getId().equals(req.getCredentialId()))
                .findFirst()
                .orElse(null);

        if (credential != null &&
                credential.getExpiryDate() != null &&
                credential.getExpiryDate().isBefore(LocalDate.now())) {

            req.setStatus("FAILED");
        } else {
            req.setStatus("SUCCESS");
        }

        AuditTrailRecord audit = new AuditTrailRecord();
        audit.setCredentialId(req.getCredentialId());
        auditService.logEvent(audit);

        return requestRepo.save(req);
    }

    @Override
    public List<VerificationRequest> getRequestsByCredential(Long credentialId) {
        return requestRepo.findByCredentialId(credentialId);
    }
}
