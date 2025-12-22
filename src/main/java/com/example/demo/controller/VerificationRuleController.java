package com.example.demo.controller;

import com.example.demo.entity.VerificationRule;
import com.example.demo.service.VerificationRuleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rules")
public class VerificationRuleController {

    private final VerificationRuleService service;

    public VerificationRuleController(
            VerificationRuleService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<VerificationRule> create(
            @Valid @RequestBody VerificationRule rule) {
        return ResponseEntity.ok(service.createRule(rule));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VerificationRule> getById(
            @PathVariable Long id) {
        return ResponseEntity.ok(service.getRuleById(id));
    }

    @GetMapping
    public ResponseEntity<List<VerificationRule>> getAll() {
        return ResponseEntity.ok(service.getAllRules());
    }

    @PutMapping("/{id}")
    public ResponseEntity<VerificationRule> update(
            @PathVariable Long id,
            @Valid @RequestBody VerificationRule rule) {
        return ResponseEntity.ok(service.updateRule(id, rule));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable Long id) {
        service.deleteRule(id);
        return ResponseEntity.ok("Deleted successfully");
    }
}
