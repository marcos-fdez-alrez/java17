package es.vass.talks.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;


class SealedClassesTest
{

    Logger logger = LoggerFactory.getLogger(SealedClassesTest.class);

    @DisplayName("Test sealed modifiers in MotorVehicle hierarchy")
    @Test
    void checkSealedModifiers()
    {
        /*
            Reflection API - Dos nuevos métodos añadidos a java.lang.Class

            - isSealed()
            - getPermittedSubclasses()

         */

        Assertions.assertAll(
                () -> assertThat(MotorVehicle.class.isSealed()).isEqualTo(true),
                () -> assertThat(Motorcycle.class.isSealed()).isEqualTo(true),
                () -> assertThat(Motorcycle.class.getPermittedSubclasses())
                        .contains(TinyMotorcycle.class)
        );

    }

    @DisplayName("Check number of Wheels")
    @Nested
    class WheelsCounter {

        @DisplayName("Test MotorVehicle direct subclasses number of wheels")
        @ParameterizedTest
        @MethodSource("provideParameters")
        public void testParametersFromMethod(MotorVehicle vehicle) {
            switch (vehicle) {
                case Motorcycle moto:
                    assertThat(moto.getNumberOfWheels()).isEqualTo(3);
                    break;
                case Ebike e:
                    assertThat(e.getNumberOfWheels()).isEqualTo(2);
                    break;
                default:
                    fail("This type of motorVehicle was not taking into account. Check switch cases!");
            }


        }

        private static Stream<Arguments> provideParameters() {
            return Stream.of(
                    Arguments.of(new Ebike()),
                    Arguments.of(new Motorcycle()));
        }



        @DisplayName("Check Bike")
        @Test
        void checkBikeNumberOfWheels()
        {
            assertThat(new Bike().getNumberOfWheels()).isEqualTo(2);
        }

    }

}
