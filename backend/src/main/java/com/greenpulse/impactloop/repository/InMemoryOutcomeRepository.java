package com.greenpulse.impactloop.repository;

import com.greenpulse.impactloop.entity.Outcome;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryOutcomeRepository implements OutcomeRepository {
    
    private final ConcurrentHashMap<String, Outcome> store = new ConcurrentHashMap<>();

    @Override
    public void save(Outcome outcome) {
        store.put(outcome.getOutcomeId(), outcome);
    }

    @Override
    public List<Outcome> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Outcome findById(String outcomeId) {
        return store.get(outcomeId);
    }

    @Override
    public Outcome findByInterventionId(String interventionId) {
        return store.values().stream()
                .filter(o -> o.getInterventionId().equals(interventionId))
                .findFirst()
                .orElse(null);
    }
}
