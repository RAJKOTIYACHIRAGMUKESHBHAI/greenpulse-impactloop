package com.greenpulse.impactloop.outcome;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OutcomeStatusResolverTest {

    private final OutcomeStatusResolver resolver =
            new OutcomeStatusResolver();

    @Test
    void shouldReturnPositiveFor50PercentOrMoreReduction() {
        OutcomeStatus result = resolver.resolve(4, 2, 50.0);

        assertEquals(OutcomeStatus.POSITIVE, result);
    }

    @Test
    void shouldReturnPositiveFor75PercentReduction() {
        OutcomeStatus result = resolver.resolve(4, 1, 75.0);

        assertEquals(OutcomeStatus.POSITIVE, result);
    }

    @Test
    void shouldReturnWeakForLessThan50PercentReduction() {
        OutcomeStatus result = resolver.resolve(10, 7, 30.0);

        assertEquals(OutcomeStatus.WEAK, result);
    }

    @Test
    void shouldReturnUnclearWhenThereIsNoBaseline() {
        OutcomeStatus result = resolver.resolve(0, 0, 0.0);

        assertEquals(OutcomeStatus.UNCLEAR, result);
    }

    @Test
    void shouldReturnWeakWhenIncidentsIncrease() {
        OutcomeStatus result = resolver.resolve(4, 6, -50.0);

        assertEquals(OutcomeStatus.WEAK, result);
    }

    @Test
    void shouldReturnUnclearForNegativeAfterIncidents() {
        OutcomeStatus result = resolver.resolve(4, -1, 100.0);

        assertEquals(OutcomeStatus.UNCLEAR, result);
    }
}