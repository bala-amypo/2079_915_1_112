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

    // CREATE
    @PostMapping
    public CredentialRecord save(@RequestBody CredentialRecord record) {
        return service.save(record);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public CredentialRecord get(@PathVariable long id) {
        return service.get(id);
    }

    // GET ALL
    @GetMapping
    public List<CredentialRecord> getAllRecords() {
        return service.getAllRecords();
    }

    // UPDATE
    @PutMapping("/{id}")
    public CredentialRecord updateRecord(
            @PathVariable long id,
            @RequestBody CredentialRecord record
    ) {
        return service.updateRecord(id, record);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteRecord(@PathVariable long id) {
        service.deleteRecord(id);
    }

    // GET BY HOLDER
    @GetMapping("/holder/{holderId}")
    public List<CredentialRecord> findByHolderId(@PathVariable long holderId) {
        return service.findByHolderId(holderId);
    }

    // GET BY CODE
    @GetMapping("/code/{code}")
    public CredentialRecord findByCode(@PathVariable String code) {
        return service.findByCode(code);
    }
}
