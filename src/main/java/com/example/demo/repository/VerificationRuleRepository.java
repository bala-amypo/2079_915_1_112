package com.example.demo.repository;

import java.util.*;
import com.example.demo.entity.*;

public interface VerificationRuleRepository {
    VerificationRule save(VerificationRule r);
    List<VerificationRule> findByActiveTrue();
}
