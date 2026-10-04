package com.greenpulse.impactloop.service;

import com.greenpulse.impactloop.dto.CreateOutcomeRequest;
import com.greenpulse.impactloop.dto.OutcomeResponse;
import com.greenpulse.impactloop.entity.Outcome;
import com.greenpulse.impactloop.repository.OutcomeRepository;
import com.greenpulse.impactloop.outcome.ConfidenceLevel;
import com.greenpulse.impactloop.outcome.NextAction;
import com.greenpulse.impactloop.outcome.OutcomeEngine;
import com.greenpulse.impactloop.outcome.OutcomeStatus;
import com.greenpulse.impactloop.outcome.OutcomeStatusResolver;
import com.greenpulse.impactloop.outcome.ConfidenceResolver;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OutcomeService {

    private final OutcomeRepository outcomeRepository;
    private final OutcomeEngine outcomeEngine;
    private final OutcomeStatusResolver outcomeStatusResolver;
    private final ConfidenceResolver confidenceResolver;

    public OutcomeService(OutcomeRepository outcomeRepository) {
        this.outcomeRepository = outcomeRepository;
        this.outcomeEngine = new OutcomeEngine();
        this.outcomeStatusResolver = new OutcomeStatusResolver();
        this.confidenceResolver = new ConfidenceResolver();
    }

    public OutcomeResponse createOutcome(CreateOutcomeRequest request) {
        String outcomeId = "OUT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        int beforeIncidents = request.getBeforeIncidents() != null
                ? request.getBeforeIncidents()
                : 0;

        int afterIncidents = request.getAfterIncidents() != null
                ? request.getAfterIncidents()
                : 0;

        double observedReduction =
                new com.greenpulse.impactloop.outcome.ObservedReductionCalculator()
                        .calculate(beforeIncidents, afterIncidents);

        OutcomeStatus outcomeStatus =
                outcomeStatusResolver.resolve(
                        beforeIncidents,
                        afterIncidents,
                        observedReduction
                );

        ConfidenceLevel confidence =
                confidenceResolver.resolve(
                        beforeIncidents,
                        afterIncidents
                );

        NextAction nextAction;

        if (confidence == ConfidenceLevel.LOW) {
            nextAction = NextAction.COLLECT_MORE_DATA;
        } else if (outcomeStatus == OutcomeStatus.POSITIVE) {
            nextAction = NextAction.MONITOR;
        } else if (outcomeStatus == OutcomeStatus.UNCLEAR) {
            nextAction = NextAction.REINSPECT;
        } else {
            nextAction = NextAction.STRUCTURAL_INSPECTION;
        }

        com.greenpulse.impactloop.outcome.Outcome intelligenceOutcome =
                outcomeEngine.calculateOutcome(
                        outcomeId,
                        request.getInterventionId(),
                        beforeIncidents,
                        afterIncidents,
                        outcomeStatus,
                        request.getDurationReduction(),
                        confidence,
                        request.getRecurring() != null && request.getRecurring(),
                        nextAction,
                        Instant.now().toString()
                );

        Outcome outcome = new Outcome(
                intelligenceOutcome.getOutcomeId(),
                intelligenceOutcome.getInterventionId(),
                intelligenceOutcome.getBeforeIncidents(),
                intelligenceOutcome.getAfterIncidents(),
                intelligenceOutcome.getObservedReduction(),
                intelligenceOutcome.getDurationReduction(),
                intelligenceOutcome.getOutcome().name(),
                intelligenceOutcome.getConfidence().name(),
                intelligenceOutcome.isRecurring(),
                intelligenceOutcome.getNextAction().name(),
                intelligenceOutcome.getCreatedAt()
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