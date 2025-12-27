package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class AuditTrailRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String event;
    private Long referenceId;
    private LocalDateTime loggedAt = LocalDateTime.now();

    public AuditTrailRecord() {}

    public AuditTrailRecord(String event, Long referenceId) {
        this.event = event;
        this.referenceId = referenceId;
    }

    public Long getId() { return id; }

    public String getEvent() { return event; }
    public void setEvent(String event) { this.event = event; }

    public Long getReferenceId() { return referenceId; }
    public void setReferenceId(Long referenceId) { this.referenceId = referenceId; }

    public LocalDateTime getLoggedAt() { return loggedAt; }
    public void setLoggedAt(LocalDateTime loggedAt) { this.loggedAt = loggedAt; }
}
