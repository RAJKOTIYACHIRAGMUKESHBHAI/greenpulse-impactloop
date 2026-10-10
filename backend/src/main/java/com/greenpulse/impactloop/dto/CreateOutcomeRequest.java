package com.greenpulse.impactloop.dto;

public class CreateOutcomeRequest {
    
    private String interventionId;
    private Integer beforeIncidents;
    private Integer afterIncidents;
    private Double observedReduction;
    private Double durationReduction;
    private String outcome;
    private String confidence;
    private Boolean recurring;
    private String nextAction;

    public CreateOutcomeRequest() {
    }

    public String getInterventionId() {
        return interventionId;
    }

    public void setInterventionId(String interventionId) {
        this.interventionId = interventionId;
    }

    public Integer getBeforeIncidents() {
        return beforeIncidents;
    }

    public void setBeforeIncidents(Integer beforeIncidents) {
        this.beforeIncidents = beforeIncidents;
    }

    public Integer getAfterIncidents() {
        return afterIncidents;
    }

    public void setAfterIncidents(Integer afterIncidents) {
        this.afterIncidents = afterIncidents;
    }

    public Double getObservedReduction() {
        return observedReduction;
    }

    public void setObservedReduction(Double observedReduction) {
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

    public Boolean getRecurring() {
        return recurring;
    }

    public void setRecurring(Boolean recurring) {
        this.recurring = recurring;
    }

    public String getNextAction() {
        return nextAction;
    }

    public void setNextAction(String nextAction) {
        this.nextAction = nextAction;
    }
}
