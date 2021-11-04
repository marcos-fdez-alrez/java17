package es.vass.talks.service.impl;

import es.vass.talks.service.CalculatorService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CalculatorImplTest {

    static CalculatorService calculatorService;

    @BeforeAll
    static void init(){
        calculatorService = new CalculatorImpl();
    }

    @DisplayName("JEP 306 - strictfp is not required / not affected by platform - sum")
    @Test
    void sum() {
        assertThat(calculatorService.sum(23e10, 98e17)).isEqualTo(9.800000230000001E18);
    }

    @DisplayName("JEP 306 - strictfp is not required / not affected by platform - diff - ")
    @Test
    void diff() {
        assertThat(calculatorService.diff(Double.MAX_VALUE, 1.56)).isEqualTo(1.7976931348623157E308);
    }
}