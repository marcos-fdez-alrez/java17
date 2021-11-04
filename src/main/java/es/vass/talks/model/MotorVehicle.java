package es.vass.talks.model;

sealed interface MotorVehicle permits Motorcycle, Ebike {

    void startEngine();

    Integer getNumberOfWheels();

}
