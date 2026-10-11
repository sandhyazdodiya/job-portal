
package com.example.job_portal_management_api.controller;

import com.example.job_portal_management_api.model.Candidate;
import com.example.job_portal_management_api.service.CandidateService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

    private final CandidateService service;

    public CandidateController(CandidateService service) {
        this.service = service;
    }

    @GetMapping
    public List<Candidate> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidate> getById(
            @PathVariable Long id) {

        Candidate candidate = service.getById(id);

        if (candidate == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(candidate);
    }

    @PostMapping
    public ResponseEntity<Candidate> create(
            @RequestBody Candidate candidate) {

        Candidate saved = service.create(candidate);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Candidate> update(
            @PathVariable Long id,
            @RequestBody Candidate candidate) {

        Candidate updated = service.update(id, candidate);

        if (updated == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        if (!service.delete(id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
