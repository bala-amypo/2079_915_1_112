package com.example.demo.repository;

import com.example.demo.entity.VerificationRequest;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
@Repository
public interface VerificationRequestRepository {

    VerificationRequest save(VerificationRequest request);

    Optional<VerificationRequest> findById(Long id);

    List<VerificationRequest> findByCredentialId(Long credentialId);
}
