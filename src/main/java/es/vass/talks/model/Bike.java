package es.vass.talks.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bike
//        implements MotorVehicle
    {

    Logger logger = LoggerFactory.getLogger(Bike.class);

    public void startEngine() {
        logger.info("Unable to start the engine!");
    }

    public Integer getNumberOfWheels() {
        return 2;
    }
}
