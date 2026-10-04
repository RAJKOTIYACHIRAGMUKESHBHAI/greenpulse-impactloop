package com.greenpulse.impactloop.outcome;

public class Outcome {

    private final String outcomeId;
    private final String interventionId;
    private final OutcomeStatus outcome;
    private final int beforeIncidents;
    private final int afterIncidents;
    private final double observedReduction;
    private final Double durationReduction;
    private final ConfidenceLevel confidence;
    private final boolean recurring;
    private final NextAction nextAction;
    private final String createdAt;

    public Outcome(
            String outcomeId,
            String interventionId,
            OutcomeStatus outcome,
            int beforeIncidents,
            int afterIncidents,
            double observedReduction,
            Double durationReduction,
            ConfidenceLevel confidence,
            boolean recurring,
            NextAction nextAction,
            String createdAt
    ) {
        this.outcomeId = outcomeId;
        this.interventionId = interventionId;
        this.outcome = outcome;
        this.beforeIncidents = beforeIncidents;
        this.afterIncidents = afterIncidents;
        this.observedReduction = observedReduction;
        this.durationReduction = durationReduction;
        this.confidence = confidence;
        this.recurring = recurring;
        this.nextAction = nextAction;
        this.createdAt = createdAt;
    }

    public String getOutcomeId() {
        return outcomeId;
    }

    public String getInterventionId() {
        return interventionId;
    }

    public OutcomeStatus getOutcome() {
        return outcome;
    }

    public int getBeforeIncidents() {
        return beforeIncidents;
    }

    public int getAfterIncidents() {
        return afterIncidents;
    }

    public double getObservedReduction() {
        return observedReduction;
    }

    public Double getDurationReduction() {
        return durationReduction;
    }

    public ConfidenceLevel getConfidence() {
        return confidence;
    }

    public boolean isRecurring() {
        return recurring;
    }

    public NextAction getNextAction() {
        return nextAction;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}