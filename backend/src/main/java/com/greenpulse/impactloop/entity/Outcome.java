package com.greenpulse.impactloop.entity;

public class Outcome {
    
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

    public Outcome() {
    }

    public Outcome(
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

    public void setOutcomeId(String outcomeId) {
        this.outcomeId = outcomeId;
    }

    public String getInterventionId() {
        return interventionId;
    }

    public void setInterventionId(String interventionId) {
        this.interventionId = interventionId;
    }

    public int getBeforeIncidents() {
        return beforeIncidents;
    }

    public void setBeforeIncidents(int beforeIncidents) {
        this.beforeIncidents = beforeIncidents;
    }

    public int getAfterIncidents() {
        return afterIncidents;
    }

    public void setAfterIncidents(int afterIncidents) {
        this.afterIncidents = afterIncidents;
    }

    public double getObservedReduction() {
        return observedReduction;
    }

    public void setObservedReduction(double observedReduction) {
        this.observedReduction = observedReduction;
    }

    public Double getDurationReduction() {
        return durationReduction;
    }

    public void setDurationReduction(Double durationReduction) {
        this.durationReduction = durationReduction;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public String getConfidence() {
        return confidence;
    }

    public void setConfidence(String confidence) {
        this.confidence = confidence;
    }

    public boolean isRecurring() {
        return recurring;
    }

    public void setRecurring(boolean recurring) {
        this.recurring = recurring;
    }

    public String getNextAction() {
        return nextAction;
    }

    public void setNextAction(String nextAction) {
        this.nextAction = nextAction;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
