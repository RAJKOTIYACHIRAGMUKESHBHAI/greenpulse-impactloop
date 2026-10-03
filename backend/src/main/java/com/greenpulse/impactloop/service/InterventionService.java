package com.greenpulse.impactloop.service;

import com.greenpulse.impactloop.dto.CreateInterventionRequest;
import com.greenpulse.impactloop.dto.InterventionResponse;
import com.greenpulse.impactloop.dto.UpdateInterventionRequest;
import com.greenpulse.impactloop.entity.Intervention;
import com.greenpulse.impactloop.repository.InterventionRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class InterventionService {

    private final InterventionRepository interventionRepository;

    public InterventionService(InterventionRepository interventionRepository) {
        this.interventionRepository = interventionRepository;
    }

    public InterventionResponse createIntervention(CreateInterventionRequest request) {
        String interventionId = "INT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        
        Intervention intervention = new Intervention(
                interventionId,
                request.getIssueId(),
                request.getActionType(),
                request.getAssignedTeam(),
                "ASSIGNED",
                null,
                null
        );

        interventionRepository.save(intervention);
        return toResponse(intervention);
    }

    public InterventionResponse getInterventionById(String interventionId) {
        Intervention intervention = interventionRepository.findById(interventionId);
        if (intervention == null) {
            return null;
        }
        return toResponse(intervention);
    }

    public InterventionResponse updateIntervention(String interventionId, UpdateInterventionRequest request) {
        Intervention intervention = interventionRepository.findById(interventionId);
        if (intervention == null) {
            return null;
        }

        intervention.setStatus(request.getStatus());
        
        if ("IN_PROGRESS".equals(request.getStatus()) && intervention.getStartedAt() == null) {
            intervention.setStartedAt(Instant.now().toString());
        }
        
        if ("COMPLETED".equals(request.getStatus()) && intervention.getCompletedAt() == null) {
            intervention.setCompletedAt(Instant.now().toString());
        }

        interventionRepository.save(intervention);
        return toResponse(intervention);
    }

    public List<InterventionResponse> getInterventionsByIssueId(String issueId) {
        List<Intervention> interventions = interventionRepository.findByIssueId(issueId);
        List<InterventionResponse> responses = new ArrayList<>();
        
        for (Intervention intervention : interventions) {
            responses.add(toResponse(intervention));
        }
        
        return responses;
    }

    private InterventionResponse toResponse(Intervention intervention) {
        return new InterventionResponse(
                intervention.getInterventionId(),
                intervention.getIssueId(),
                intervention.getActionType(),
                intervention.getAssignedTeam(),
                intervention.getStatus(),
                intervention.getStartedAt(),
                intervention.getCompletedAt()
        );
    }
}
