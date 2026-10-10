package com.greenpulse.impactloop.repository;

import com.greenpulse.impactloop.entity.Intervention;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class InMemoryInterventionRepository implements InterventionRepository {
    
    private final ConcurrentHashMap<String, Intervention> store = new ConcurrentHashMap<>();

    @Override
    public void save(Intervention intervention) {
        store.put(intervention.getInterventionId(), intervention);
    }

    @Override
    public List<Intervention> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Intervention findById(String interventionId) {
        return store.get(interventionId);
    }

    @Override
    public List<Intervention> findByIssueId(String issueId) {
        return store.values().stream()
                .filter(i -> i.getIssueId().equals(issueId))
                .collect(Collectors.toList());
    }
}
