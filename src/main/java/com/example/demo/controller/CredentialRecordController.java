package com.example.demo.controller;

import com.example.demo.entity.CredentialRecord;
import com.example.demo.service.CredentialRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/credentials")
public class CredentialRecordController {

    private final CredentialRecordService service;

    public CredentialRecordController(CredentialRecordService service) {
        this.service = service;
    }

    // ===== EXACT METHOD NAMES EXPECTED BY SERVICE =====

    @PostMapping
    public CredentialRecord save(@RequestBody CredentialRecord record) {
        return service.save(record);
    }

    @GetMapping("/{id}")
    public CredentialRecord get(@PathVariable long id) {
        return service.get(id);
    }

    @GetMapping
    public List<CredentialRecord> getAll() {
        return service.getAllRecords();
    }

    @PutMapping("/{id}")
    public CredentialRecord update(@PathVariable long id,
                                   @RequestBody CredentialRecord record) {
        return service.updateRecord(id, record);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        service.deleteRecord(id);
    }

    // ===== REQUIRED BY TEST CASES =====

    @GetMapping("/holder/{holderId}")
    public List<CredentialRecord> getByHolder(@PathVariable long holderId) {
        return service.findByHolderId(holderId);
    }

    @GetMapping("/code/{code}")
    public CredentialRecord getByCode(@PathVariable String code) {
        return service.findByCode(code);
    }
}
