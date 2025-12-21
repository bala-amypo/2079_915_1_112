package com.example.demo.service;

import com.example.demo.entity.VerificationRule;
import java.util.List;

public interface VerificationRuleService {

    VerificationRule createRule(VerificationRule rule);

    VerificationRule getRuleById(Long id);

    List<VerificationRule> getAllRules();

    VerificationRule updateRule(Long id, VerificationRule rule);

    void deleteRule(Long id);
}
