package com.greenpulse.impactloop.outcome;

public class ObservedReductionCalculator {

    public double calculate(int beforeIncidents, int afterIncidents) {

        if (beforeIncidents < 0 || afterIncidents < 0) {
            throw new IllegalArgumentException(
                    "Incident counts cannot be negative."
            );
        }

        if (beforeIncidents == 0) {
            return 0.0;
        }

        return ((double) (beforeIncidents - afterIncidents)
                / beforeIncidents) * 100.0;
    }
}