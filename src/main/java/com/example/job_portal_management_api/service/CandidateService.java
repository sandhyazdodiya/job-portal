
package com.example.job_portal_management_api.service;

import com.example.job_portal_management_api.model.Candidate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CandidateService {

    private final Map<Long, Candidate> candidates =
            new ConcurrentHashMap<>();

    private final AtomicLong nextId = new AtomicLong(1);

    public List<Candidate> getAll() {
        return List.copyOf(candidates.values());
    }

    public Candidate getById(Long id) {
        return candidates.get(id);
    }

    public Candidate create(Candidate candidate) {
        Long id = nextId.getAndIncrement();

        Candidate saved = new Candidate(
                id,
                candidate.name(),
                candidate.email(),
                candidate.skills(),
                candidate.jobTitle(),
                candidate.status()
        );

        candidates.put(id, saved);
        return saved;
    }

    public Candidate update(Long id, Candidate candidate) {
        if (!candidates.containsKey(id)) {
            return null;
        }

        Candidate updated = new Candidate(
                id,
                candidate.name(),
                candidate.email(),
                candidate.skills(),
                candidate.jobTitle(),
                candidate.status()
        );

        candidates.put(id, updated);
        return updated;
    }

    public boolean delete(Long id) {
        return candidates.remove(id) != null;
    }
}
