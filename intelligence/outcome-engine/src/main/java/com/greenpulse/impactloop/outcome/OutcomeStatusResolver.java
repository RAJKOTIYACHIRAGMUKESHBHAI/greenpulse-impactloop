package com.greenpulse.impactloop.outcome;

public class OutcomeStatusResolver {

    private static final double POSITIVE_THRESHOLD_PERCENT = 50.0;

    public OutcomeStatus resolve(
            int beforeIncidents,
            int afterIncidents,
            double observedReduction
    ) {

        // No valid baseline for comparison.
        if (beforeIncidents <= 0) {
            return OutcomeStatus.UNCLEAR;
        }

        // Invalid incident count.
        if (afterIncidents < 0) {
            return OutcomeStatus.UNCLEAR;
        }

        // Reduction should never be treated as positive
        // when incidents increased.
        if (afterIncidents > beforeIncidents) {
            return OutcomeStatus.WEAK;
        }

        if (observedReduction >= POSITIVE_THRESHOLD_PERCENT) {
            return OutcomeStatus.POSITIVE;
        }

        return OutcomeStatus.WEAK;
    }
}