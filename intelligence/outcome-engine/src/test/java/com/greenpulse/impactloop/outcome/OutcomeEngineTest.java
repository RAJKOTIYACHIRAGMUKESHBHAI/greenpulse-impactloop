package com.greenpulse.impactloop.outcome;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class OutcomeEngineTest {

    private final OutcomeEngine engine = new OutcomeEngine();

    @Test
    void shouldCreatePositiveOutcomeWith75PercentReduction() {

        Outcome result = engine.calculateOutcome(
                4,
                1,
                OutcomeStatus.POSITIVE,
                ConfidenceLevel.MEDIUM,
                false,
                NextAction.MONITOR
        );

        assertEquals(OutcomeStatus.POSITIVE, result.getOutcome());
        assertEquals(75.0, result.getObservedReductionPercent(), 0.001);
        assertEquals(ConfidenceLevel.MEDIUM, result.getConfidence());
        assertFalse(result.isRecurrence());
        assertEquals(NextAction.MONITOR, result.getNextAction());
    }
}