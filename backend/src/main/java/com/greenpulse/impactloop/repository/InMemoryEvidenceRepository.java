package com.greenpulse.impactloop.repository;

import com.greenpulse.impactloop.entity.Evidence;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class InMemoryEvidenceRepository implements EvidenceRepository {
    
    private final ConcurrentHashMap<String, Evidence> store = new ConcurrentHashMap<>();

    @Override
    public void save(Evidence evidence) {
        store.put(evidence.getEvidenceId(), evidence);
    }

    @Override
    public List<Evidence> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Evidence findById(String evidenceId) {
        return store.get(evidenceId);
    }

    @Override
    public List<Evidence> findByInterventionId(String interventionId) {
        return store.values().stream()
                .filter(e -> e.getInterventionId().equals(interventionId))
                .collect(Collectors.toList());
    }
}
