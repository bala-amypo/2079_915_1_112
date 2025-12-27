package com.example.demo.service.impl;

import com.example.demo.entity.CredentialHolderProfile;
import com.example.demo.repository.CredentialHolderProfileRepository;
import com.example.demo.service.CredentialHolderProfileService;
import org.springframework.stereotype.Service;

@Service
public class CredentialHolderProfileServiceImpl
        implements CredentialHolderProfileService {

    private final CredentialHolderProfileRepository repository;

    public CredentialHolderProfileServiceImpl(
            CredentialHolderProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public CredentialHolderProfile createHolder(
            CredentialHolderProfile profile) {
        return repository.save(profile);
    }

    @Override
    public CredentialHolderProfile getHolderById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Holder not found"));
    }

    @Override
    public CredentialHolderProfile updateStatus(
            Long id, boolean active) {

        CredentialHolderProfile holder = getHolderById(id);
        holder.setActive(active);
        return repository.save(holder);
    }
}
