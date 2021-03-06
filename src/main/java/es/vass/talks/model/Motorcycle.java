package es.vass.talks.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public sealed class Motorcycle implements MotorVehicle permits TinyMotorcycle {

    Logger logger = LoggerFactory.getLogger(Motorcycle.class);

    public static final String NAME = "Can-Am";

    @Override
    public void startEngine() {
        logger.info("Starting the engine!");
    }

    @Override
    public Integer getNumberOfWheels() {
        return 3;
    }

}
