package com.example.demo.repository;

import com.example.demo.entity.VerificationRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VerificationRuleRepository
        extends JpaRepository<VerificationRule, Long> {

    List<VerificationRule> findByActiveTrue();
}
