package com.example.demo.service.impl;

import com.example.demo.entity.AuditTrailRecord;
import com.example.demo.entity.VerificationRequest;
import com.example.demo.repository.AuditTrailRecordRepository;
import com.example.demo.repository.VerificationRequestRepository;
import com.example.demo.service.VerificationRequestService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VerificationRequestServiceImpl implements VerificationRequestService {

    private final VerificationRequestRepository requestRepository;
    private final AuditTrailRecordRepository auditRepository;

    public VerificationRequestServiceImpl(
            VerificationRequestRepository requestRepository,
            AuditTrailRecordRepository auditRepository) {
        this.requestRepository = requestRepository;
        this.auditRepository = auditRepository;
    }

    @Override
    public VerificationRequest initiateVerification(VerificationRequest request) {

        request.setStatus(VerificationRequest.VerificationStatus.PENDING);
        request.setRequestedAt(LocalDateTime.now());

        VerificationRequest saved = requestRepository.save(request);

        AuditTrailRecord audit = new AuditTrailRecord();
        audit.setCredentialId(saved.getCredentialId());
        audit.setAction("VERIFICATION_REQUESTED");

        auditRepository.save(audit);

        return saved;
    }

    @Override
    public VerificationRequest processVerification(Long requestId) {

        VerificationRequest request =
                requestRepository.findById(requestId).orElseThrow();

        request.setStatus(VerificationRequest.VerificationStatus.APPROVED);

        VerificationRequest updated = requestRepository.save(request);

        AuditTrailRecord audit = new AuditTrailRecord();
        audit.setCredentialId(updated.getCredentialId());
        audit.setAction("VERIFICATION_APPROVED");

        auditRepository.save(audit);

        return updated;
    }

    @Override
    public List<VerificationRequest> getRequestsByCredential(Long credentialId) {
        return requestRepository.findByCredentialId(credentialId);
    }
}
