package com.example.demo.repository;

import java.util.*;
import com.example.demo.entity.*;

public interface VerificationRequestRepository {
    VerificationRequest save(VerificationRequest r);
    Optional<VerificationRequest> findById(Long id);
    List<VerificationRequest> findByCredentialId(Long cid);
}
