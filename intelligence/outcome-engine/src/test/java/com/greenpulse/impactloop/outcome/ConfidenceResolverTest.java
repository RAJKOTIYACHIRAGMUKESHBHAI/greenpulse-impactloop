package com.greenpulse.impactloop.outcome;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConfidenceResolverTest {

    private final ConfidenceResolver resolver =
            new ConfidenceResolver();

    @Test
    void shouldReturnLowConfidenceForSmallBaseline() {
        ConfidenceLevel result = resolver.resolve(2, 1);

        assertEquals(ConfidenceLevel.LOW, result);
    }

    @Test
    void shouldReturnMediumConfidenceForThreeEvents() {
        ConfidenceLevel result = resolver.resolve(3, 1);

        assertEquals(ConfidenceLevel.MEDIUM, result);
    }

    @Test
    void shouldReturnMediumConfidenceForFourEvents() {
        ConfidenceLevel result = resolver.resolve(4, 1);

        assertEquals(ConfidenceLevel.MEDIUM, result);
    }

    @Test
    void shouldReturnHighConfidenceForFiveOrMoreEvents() {
        ConfidenceLevel result = resolver.resolve(5, 1);

        assertEquals(ConfidenceLevel.HIGH, result);
    }

    @Test
    void shouldReturnLowConfidenceForNegativeInput() {
        ConfidenceLevel result = resolver.resolve(-1, 1);

        assertEquals(ConfidenceLevel.LOW, result);
    }
}