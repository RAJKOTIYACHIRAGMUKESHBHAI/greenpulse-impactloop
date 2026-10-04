package com.greenpulse.impactloop.outcome;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class OutcomeEngineTest {

    private final OutcomeEngine engine = new OutcomeEngine();

    @Test
    void shouldCreatePositiveOutcomeWith75PercentReduction() {

        Outcome result = engine.calculateOutcome(
                "OUT-001",
                "INT-221",
                4,
                1,
                OutcomeStatus.POSITIVE,
                72.0,
                ConfidenceLevel.MEDIUM,
                false,
                NextAction.MONITOR,
                "2026-10-08T15:00:00Z"
        );

        assertEquals("OUT-001", result.getOutcomeId());
        assertEquals("INT-221", result.getInterventionId());
        assertEquals(OutcomeStatus.POSITIVE, result.getOutcome());

        assertEquals(4, result.getBeforeIncidents());
        assertEquals(1, result.getAfterIncidents());

        assertEquals(75.0, result.getObservedReduction(), 0.001);
        assertEquals(72.0, result.getDurationReduction(), 0.001);

        assertEquals(ConfidenceLevel.MEDIUM, result.getConfidence());
        assertFalse(result.isRecurring());
        assertEquals(NextAction.MONITOR, result.getNextAction());
        assertEquals("2026-10-08T15:00:00Z", result.getCreatedAt());
    }
}