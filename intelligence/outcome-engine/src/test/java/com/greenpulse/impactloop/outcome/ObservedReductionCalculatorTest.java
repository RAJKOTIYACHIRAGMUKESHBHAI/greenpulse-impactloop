package com.greenpulse.impactloop.outcome;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ObservedReductionCalculatorTest {

    private final ObservedReductionCalculator calculator =
            new ObservedReductionCalculator();

    @Test
    void shouldCalculate75PercentReduction() {
        double result = calculator.calculate(4, 1);

        assertEquals(75.0, result, 0.001);
    }

    @Test
    void shouldCalculate50PercentReduction() {
        double result = calculator.calculate(10, 5);

        assertEquals(50.0, result, 0.001);
    }

    @Test
    void shouldReturnZeroWhenThereAreNoBeforeIncidents() {
        double result = calculator.calculate(0, 0);

        assertEquals(0.0, result, 0.001);
    }

    @Test
    void shouldRejectNegativeIncidentCounts() {
        assertThrows(
                IllegalArgumentException.class,
                () -> calculator.calculate(-1, 0)
        );
    }
}