package es.vass.talks.service.impl;

import es.vass.talks.service.CalculatorService;

public strictfp class CalculatorImpl implements CalculatorService {

    @Override
    public double sum(double value1, double value2) {
        return value1 + value2;
    }

    @Override
    public double diff(double value1, double value2) {
        return value1 - value2;
    }
}
