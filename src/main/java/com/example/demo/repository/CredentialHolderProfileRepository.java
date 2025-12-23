package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.CredentialHolderProfile;

public interface CredentialHolderProfileRepository
        extends JpaRepository<CredentialHolderProfile, Long> {
}
