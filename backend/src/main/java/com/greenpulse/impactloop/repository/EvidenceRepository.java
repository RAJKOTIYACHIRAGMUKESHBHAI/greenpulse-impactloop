package com.greenpulse.impactloop.repository;

import com.greenpulse.impactloop.entity.Evidence;
import java.util.List;

public interface EvidenceRepository {
    void save(Evidence evidence);
    List<Evidence> findAll();
    Evidence findById(String evidenceId);
    List<Evidence> findByInterventionId(String interventionId);
}
