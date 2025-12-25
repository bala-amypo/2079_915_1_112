package com.example.demo.service.impl;

import com.example.demo.entity.CredentialRecord;
import com.example.demo.entity.VerificationRequest;
import com.example.demo.entity.VerificationRequest.VerificationStatus;
import com.example.demo.repository.VerificationRequestRepository;
import com.example.demo.service.*;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class VerificationRequestServiceImpl implements VerificationRequestService {

    private final VerificationRequestRepository verificationRepo;
    private final CredentialRecordService credentialService;
    private final VerificationRuleService ruleService;
    private final AuditTrailService auditService;

    // ✅ MUST MATCH TEST CONSTRUCTOR
    public VerificationRequestServiceImpl(
            VerificationRequestRepository verificationRepo,
            CredentialRecordService credentialService,
            VerificationRuleService ruleService,
            AuditTrailService auditService
    ) {
        this.verificationRepo = verificationRepo;
        this.credentialService = credentialService;
        this.ruleService = ruleService;
        this.auditService = auditService;
    }

    @Override
    public VerificationRequest initiateVerification(VerificationRequest request) {
        request.setRequestedAt(LocalDateTime.now());
        return verificationRepo.save(request);
    }

    @Override
    public VerificationRequest processVerification(Long credentialId) {

        CredentialRecord credential = credentialService.getCredentialById(credentialId);

        VerificationRequest req = new VerificationRequest();
        req.setCredentialId(credentialId);
        req.setRequestedAt(LocalDateTime.now());

        if (credential == null) {
            req.setStatus(VerificationStatus.FAILED);
        } else if (
                credential.getExpiryDate() != null &&
                credential.getExpiryDate().isBefore(LocalDate.now())
        ) {
            req.setStatus(VerificationStatus.FAILED);
        } else {
            req.setStatus(VerificationStatus.SUCCESS);
        }

        return verificationRepo.save(req);
    }

    @Override
    public List<VerificationRequest> getRequestsByCredential(Long credentialId) {
        return verificationRepo.findByCredentialId(credentialId);
    }
}
