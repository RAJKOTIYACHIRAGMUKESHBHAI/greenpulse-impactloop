package com.greenpulse.impactloop.repository;

import com.greenpulse.impactloop.entity.Outcome;
import java.util.List;

public interface OutcomeRepository {
    void save(Outcome outcome);
    List<Outcome> findAll();
    Outcome findById(String outcomeId);
    Outcome findByInterventionId(String interventionId);
}
