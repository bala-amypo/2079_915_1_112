package com.example.demo.repository;

import java.time.LocalDate;
import java.util.*;
import com.example.demo.entity.*;

public interface CredentialHolderProfileRepository {
    Optional<CredentialHolderProfile> findById(Long id);
    CredentialHolderProfile save(CredentialHolderProfile p);
}
