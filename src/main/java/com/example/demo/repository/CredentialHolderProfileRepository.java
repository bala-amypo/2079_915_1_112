package com.example.demo.repository;

import com.example.demo.entity.CredentialHolderProfile;
import java.util.Optional;

public interface CredentialHolderProfileRepository {

    CredentialHolderProfile save(CredentialHolderProfile profile);

    Optional<CredentialHolderProfile> findById(Long id);
}
