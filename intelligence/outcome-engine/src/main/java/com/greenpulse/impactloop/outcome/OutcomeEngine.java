package com.greenpulse.impactloop.outcome;

public class OutcomeEngine {

    private final ObservedReductionCalculator reductionCalculator;

    public OutcomeEngine() {
        this.reductionCalculator = new ObservedReductionCalculator();
    }

    public Outcome calculateOutcome(
            String outcomeId,
            String interventionId,
            int beforeIncidents,
            int afterIncidents,
            OutcomeStatus outcomeStatus,
            Double durationReduction,
            ConfidenceLevel confidence,
            boolean recurring,
            NextAction nextAction,
            String createdAt
    ) {

        double observedReduction =
                reductionCalculator.calculate(
                        beforeIncidents,
                        afterIncidents
                );

        return new Outcome(
                outcomeId,
                interventionId,
                outcomeStatus,
                beforeIncidents,
                afterIncidents,
                observedReduction,
                durationReduction,
                confidence,
                recurring,
                nextAction,
                createdAt
        );
    }
}