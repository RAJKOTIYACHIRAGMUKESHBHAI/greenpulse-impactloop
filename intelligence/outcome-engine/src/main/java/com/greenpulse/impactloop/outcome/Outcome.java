package com.greenpulse.impactloop.outcome;

public class Outcome {

    private final OutcomeStatus outcome;
    private final double observedReductionPercent;
    private final ConfidenceLevel confidence;
    private final boolean recurrence;
    private final NextAction nextAction;

    public Outcome(
            OutcomeStatus outcome,
            double observedReductionPercent,
            ConfidenceLevel confidence,
            boolean recurrence,
            NextAction nextAction
    ) {
        this.outcome = outcome;
        this.observedReductionPercent = observedReductionPercent;
        this.confidence = confidence;
        this.recurrence = recurrence;
        this.nextAction = nextAction;
    }

    public OutcomeStatus getOutcome() {
        return outcome;
    }

    public double getObservedReductionPercent() {
        return observedReductionPercent;
    }

    public ConfidenceLevel getConfidence() {
        return confidence;
    }

    public boolean isRecurrence() {
        return recurrence;
    }

    public NextAction getNextAction() {
        return nextAction;
    }
}