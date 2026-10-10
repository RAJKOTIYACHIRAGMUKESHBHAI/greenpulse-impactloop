package com.greenpulse.impactloop.outcome;

public class ConfidenceResolver {

    public ConfidenceLevel resolve(
            int beforeIncidents,
            int afterIncidents
    ) {

        if (beforeIncidents < 0 || afterIncidents < 0) {
            return ConfidenceLevel.LOW;
        }

        if (beforeIncidents < 3) {
            return ConfidenceLevel.LOW;
        }

        if (beforeIncidents < 5) {
            return ConfidenceLevel.MEDIUM;
        }

        return ConfidenceLevel.HIGH;
    }
}