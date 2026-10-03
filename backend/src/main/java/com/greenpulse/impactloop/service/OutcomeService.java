package com.greenpulse.impactloop.service;

import com.greenpulse.impactloop.dto.CreateOutcomeRequest;
import com.greenpulse.impactloop.dto.OutcomeResponse;
import com.greenpulse.impactloop.entity.Outcome;
import com.greenpulse.impactloop.repository.OutcomeRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OutcomeService {

    private final OutcomeRepository outcomeRepository;

    public OutcomeService(OutcomeRepository outcomeRepository) {
        this.outcomeRepository = outcomeRepository;
    }

    public OutcomeResponse createOutcome(CreateOutcomeRequest request) {
        String outcomeId = "OUT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        
        int beforeIncidents = request.getBeforeIncidents() != null ? request.getBeforeIncidents() : 0;
        int afterIncidents = request.getAfterIncidents() != null ? request.getAfterIncidents() : 0;
        
        // Calculate observed reduction if not provided
        double observedReduction = request.getObservedReduction() != null 
            ? request.getObservedReduction() 
            : calculateObservedReduction(beforeIncidents, afterIncidents);
        
        // Validate and set outcome status
        String outcomeStatus = request.getOutcome() != null ? request.getOutcome() : determineOutcomeStatus(observedReduction);
        
        // Determine confidence based on data quality
        String confidence = request.getConfidence() != null 
            ? request.getConfidence() 
            : determineConfidence(beforeIncidents, afterIncidents);
        
        boolean recurring = request.getRecurring() != null ? request.getRecurring() : false;
        String nextAction = request.getNextAction() != null ? request.getNextAction() : determineNextAction(outcomeStatus, confidence);
        
        Outcome outcome = new Outcome(
                outcomeId,
                request.getInterventionId(),
                beforeIncidents,
                afterIncidents,
                observedReduction,
                request.getDurationReduction(),
                outcomeStatus,
                confidence,
                recurring,
                nextAction,
                Instant.now().toString()
        );

        outcomeRepository.save(outcome);
        return toResponse(outcome);
    }

    public OutcomeResponse getOutcomeById(String outcomeId) {
        Outcome outcome = outcomeRepository.findById(outcomeId);
        if (outcome == null) {
            return null;
        }
        return toResponse(outcome);
    }

    public OutcomeResponse getOutcomeByInterventionId(String interventionId) {
        Outcome outcome = outcomeRepository.findByInterventionId(interventionId);
        if (outcome == null) {
            return null;
        }
        return toResponse(outcome);
    }

    public List<OutcomeResponse> getAllOutcomes() {
        List<Outcome> outcomes = outcomeRepository.findAll();
        List<OutcomeResponse> responses = new ArrayList<>();
        
        for (Outcome outcome : outcomes) {
            responses.add(toResponse(outcome));
        }
        
        return responses;
    }

    private double calculateObservedReduction(int beforeIncidents, int afterIncidents) {
        if (beforeIncidents <= 0) {
            return 0.0;
        }
        return ((beforeIncidents - afterIncidents) * 100.0) / beforeIncidents;
    }

    private String determineOutcomeStatus(double observedReduction) {
        if (observedReduction >= 50.0) {
            return "POSITIVE";
        } else if (observedReduction >= 20.0) {
            return "WEAK";
        } else {
            return "UNCLEAR";
        }
    }

    private String determineConfidence(int beforeIncidents, int afterIncidents) {
        // Confidence based on data completeness
        if (beforeIncidents == 0 && afterIncidents == 0) {
            return "LOW";
        } else if (beforeIncidents < 3 || afterIncidents < 3) {
            return "LOW";
        } else if (beforeIncidents < 5 || afterIncidents < 5) {
            return "MEDIUM";
        } else {
            return "HIGH";
        }
    }

    private String determineNextAction(String outcomeStatus, String confidence) {
        if ("LOW".equals(confidence)) {
            return "COLLECT_MORE_DATA";
        }
        
        if ("POSITIVE".equals(outcomeStatus)) {
            return "MONITOR";
        } else if ("UNCLEAR".equals(outcomeStatus)) {
            return "REINSPECT";
        } else {
            return "STRUCTURAL_INSPECTION";
        }
    }

    private OutcomeResponse toResponse(Outcome outcome) {
        return new OutcomeResponse(
                outcome.getOutcomeId(),
                outcome.getInterventionId(),
                outcome.getBeforeIncidents(),
                outcome.getAfterIncidents(),
                outcome.getObservedReduction(),
                outcome.getDurationReduction(),
                outcome.getOutcome(),
                outcome.getConfidence(),
                outcome.isRecurring(),
                outcome.getNextAction(),
                outcome.getCreatedAt()
        );
    }
}
