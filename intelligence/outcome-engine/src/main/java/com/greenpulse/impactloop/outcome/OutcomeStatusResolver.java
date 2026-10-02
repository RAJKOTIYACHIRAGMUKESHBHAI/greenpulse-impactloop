package com.greenpulse.impactloop.outcome;

public class OutcomeStatusResolver {

    private static final double POSITIVE_THRESHOLD_PERCENT = 50.0;

    public OutcomeStatus resolve(
            int beforeIncidents,
            double observedReductionPercent
    ) {
        if (beforeIncidents <= 0) {
            return OutcomeStatus.UNCLEAR;
        }

        if (observedReductionPercent >= POSITIVE_THRESHOLD_PERCENT) {
            return OutcomeStatus.POSITIVE;
        }

        return OutcomeStatus.WEAK;
    }
}