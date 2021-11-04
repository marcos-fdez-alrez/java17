package es.vass.talks.model;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Ebike implements MotorVehicle {

    Logger logger = LoggerFactory.getLogger(Ebike.class);

    @Override
    public void startEngine() {
        logger.info("Starting the engine!");
    }

    @Override
    public Integer getNumberOfWheels() {
        return 2;
    }

}
