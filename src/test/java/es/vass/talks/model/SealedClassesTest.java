package es.vass.talks.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class SealedClassesTest
{

    @DisplayName("Test sealed modifiers in MotorVehicle hierarchy")
    @Test
    public void checkSealedModifiers()
    {
        Assertions.assertAll(
                () -> assertThat(MotorVehicle.class.isSealed()).isEqualTo(true),
                () -> assertThat(Motorcycle.class.isSealed()).isEqualTo(true),
                () -> assertThat(Motorcycle.class.getPermittedSubclasses())
                        .contains(TinyMotorcycle.class)
        );

    }

}
