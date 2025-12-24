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

    // ================= BASIC CRUD =================

    @PostMapping
    public CredentialRecord create(@RequestBody CredentialRecord record) {
        return service.create(record);
    }

    @GetMapping("/{id}")
    public CredentialRecord getById(@PathVariable long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<CredentialRecord> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    public CredentialRecord update(@PathVariable long id,
                                   @RequestBody CredentialRecord record) {
        return service.update(id, record);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        service.delete(id);
    }

    // ================= REQUIRED BY TEST CASES =================

    @GetMapping("/holder/{holderId}")
    public List<CredentialRecord> getByHolder(@PathVariable long holderId) {
        return service.getByHolder(holderId);
    }

    @GetMapping("/code/{code}")
    public CredentialRecord getByCode(@PathVariable String code) {
        return service.getByCode(code);
    }
}
