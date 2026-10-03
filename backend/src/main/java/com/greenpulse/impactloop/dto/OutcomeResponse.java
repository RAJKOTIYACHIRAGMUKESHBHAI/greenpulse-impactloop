package com.greenpulse.impactloop.dto;

public class OutcomeResponse {
    
    private String outcomeId;
    private String interventionId;
    private int beforeIncidents;
    private int afterIncidents;
    private double observedReduction;
    private Double durationReduction;
    private String outcome;
    private String confidence;
    private boolean recurring;
    private String nextAction;
    private String createdAt;

    public OutcomeResponse() {
    }

    public OutcomeResponse(
            String outcomeId,
            String interventionId,
            int beforeIncidents,
            int afterIncidents,
            double observedReduction,
            Double durationReduction,
            String outcome,
            String confidence,
            boolean recurring,
            String nextAction,
            String createdAt
    ) {
        this.outcomeId = outcomeId;
        this.interventionId = interventionId;
        this.beforeIncidents = beforeIncidents;
        this.afterIncidents = afterIncidents;
        this.observedReduction = observedReduction;
        this.durationReduction = durationReduction;
        this.outcome = outcome;
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

    public String getOutcome() {
        return outcome;
    }

    public String getConfidence() {
        return confidence;
    }

    public boolean isRecurring() {
        return recurring;
    }

    public String getNextAction() {
        return nextAction;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
