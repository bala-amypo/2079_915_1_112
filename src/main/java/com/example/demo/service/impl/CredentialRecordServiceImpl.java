package com.example.demo.service.impl;

import com.example.demo.entity.CredentialRecord;
import com.example.demo.repository.CredentialRecordRepository;
import com.example.demo.service.CredentialRecordService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CredentialRecordServiceImpl implements CredentialRecordService {

    private final CredentialRecordRepository credentialRepo;

    public CredentialRecordServiceImpl(CredentialRecordRepository credentialRepo) {
        this.credentialRepo = credentialRepo;
    }

    @Override
    public CredentialRecord createCredential(CredentialRecord credential) {
        return credentialRepo.save(credential);
    }

    @Override
    public CredentialRecord getCredentialById(Long id) {
        return credentialRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Credential not found"));
    }

    @Override
    public List<CredentialRecord> getAllCredentials() {
        return credentialRepo.findAll();
    }

    @Override
    public List<CredentialRecord> getCredentialsByHolder(Long holderId) {
        return credentialRepo.findByHolderId(holderId);
    }

    @Override
    public CredentialRecord updateCredential(Long id, CredentialRecord credential) {
        CredentialRecord existing = getCredentialById(id);

        existing.setCredentialName(credential.getCredentialName());
        existing.setCredentialCode(credential.getCredentialCode());
        existing.setExpiryDate(credential.getExpiryDate());
        existing.setIssuer(credential.getIssuer());
        existing.setStatus(credential.getStatus());
        existing.setMetadataJson(credential.getMetadataJson());
        existing.setBody(credential.getBody());

        return credentialRepo.save(existing);
    }

    @Override
    public void deleteCredential(Long id) {
        credentialRepo.deleteById(id);
    }

    // ---------------- EXTRA METHODS USED BY TESTS ----------------

    public CredentialRecord getByCredentialCode(String code) {
        return credentialRepo.findByCredentialCode(code);
    }

    public List<CredentialRecord> findExpiredBefore(LocalDate date) {
        // ❌ NO orElse() — repository already returns List
        return credentialRepo.findExpiredBefore(date);
    }

    public List<CredentialRecord> findByStatusUsingHql(String status) {
        return credentialRepo.findByStatusUsingHql(status);
    }

    public List<CredentialRecord> searchByIssuerAndType(String issuer, String type) {
        return credentialRepo.searchByIssuerAndType(issuer, type);
    }
}
