package com.greenpulse.impactloop.outcome;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OutcomeStatusResolverTest {

    private final OutcomeStatusResolver resolver =
            new OutcomeStatusResolver();

    @Test
    void shouldReturnPositiveFor50PercentOrMoreReduction() {
        OutcomeStatus result = resolver.resolve(4, 50.0);

        assertEquals(OutcomeStatus.POSITIVE, result);
    }

    @Test
    void shouldReturnPositiveFor75PercentReduction() {
        OutcomeStatus result = resolver.resolve(4, 75.0);

        assertEquals(OutcomeStatus.POSITIVE, result);
    }

    @Test
    void shouldReturnWeakForLessThan50PercentReduction() {
        OutcomeStatus result = resolver.resolve(10, 30.0);

        assertEquals(OutcomeStatus.WEAK, result);
    }

    @Test
    void shouldReturnUnclearWhenThereIsNoBaseline() {
        OutcomeStatus result = resolver.resolve(0, 75.0);

        assertEquals(OutcomeStatus.UNCLEAR, result);
    }
}