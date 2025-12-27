package com.example.demo.repository;

import com.example.demo.entity.VerificationRule;
import java.util.List;

public interface VerificationRuleRepository {

    VerificationRule save(VerificationRule rule);

    List<VerificationRule> findByActiveTrue();
}
