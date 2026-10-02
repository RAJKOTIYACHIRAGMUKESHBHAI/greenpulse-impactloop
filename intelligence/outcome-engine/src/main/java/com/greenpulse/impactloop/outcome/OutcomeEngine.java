package com.greenpulse.impactloop.outcome;

public class OutcomeEngine {

    private final ObservedReductionCalculator reductionCalculator;

    public OutcomeEngine() {
        this.reductionCalculator = new ObservedReductionCalculator();
    }

    public Outcome calculateOutcome(
            int beforeIncidents,
            int afterIncidents,
            OutcomeStatus outcomeStatus,
            ConfidenceLevel confidence,
            boolean recurrence,
            NextAction nextAction
    ) {

        double observedReduction =
                reductionCalculator.calculate(
                        beforeIncidents,
                        afterIncidents
                );

        return new Outcome(
                outcomeStatus,
                observedReduction,
                confidence,
                recurrence,
                nextAction
        );
    }
}