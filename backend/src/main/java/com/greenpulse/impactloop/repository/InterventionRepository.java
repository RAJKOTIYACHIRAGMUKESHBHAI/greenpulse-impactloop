package com.greenpulse.impactloop.repository;

import com.greenpulse.impactloop.entity.Intervention;
import java.util.List;

public interface InterventionRepository {
    void save(Intervention intervention);
    List<Intervention> findAll();
    Intervention findById(String interventionId);
    List<Intervention> findByIssueId(String issueId);
}
