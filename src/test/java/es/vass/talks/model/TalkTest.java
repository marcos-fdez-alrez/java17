package es.vass.talks.model;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;


class TalkTest
{

    Logger logger = LoggerFactory.getLogger(TalkTest.class);

    @DisplayName("JEP 406 - Sealed Classes Tests")
    @Nested
    class SealedClasses {
        @DisplayName("Test sealed modifiers in MotorVehicle hierarchy")
        @Test
        void checkSealedModifiers() {
            /*
                Reflection API - 2 new methods added to java.lang.Class

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
    }

    @DisplayName("JEP 409 - Pattern Matching for Switch Tests")
    @Nested
    class PatternMatching {

        public static final Integer CAN_AM_MOTORCYCLE_WHEELS = 3;
        public static final Integer EBIKE_MOTORCYCLE_WHEELS = 2;
        public static final Integer BIKE_MOTORCYCLE_WHEELS = 2;

        @DisplayName("Test MotorVehicle direct subclasses number of wheels")
        @ParameterizedTest
        @MethodSource("provideParameters")
        void testParametersFromMethod(MotorVehicle vehicle) {
            switch (vehicle) {
                case Motorcycle moto:
                    assertThat(moto.getNumberOfWheels()).isEqualTo(CAN_AM_MOTORCYCLE_WHEELS);
                    break;
                /* Subclasses must be before its superclasses
                case TinyMotorcycle tinyMotorcycle:
                    assertThat(tinyMotorcycle.getNumberOfWheels()).isEqualTo(CAN_AM_MOTORCYCLE_WHEELS);
                    break;
                    */
                case Ebike e:
                    assertThat(e.getNumberOfWheels()).isEqualTo(EBIKE_MOTORCYCLE_WHEELS);
                    break;
                default:
                    fail("This type of motorVehicle was not taking into account. Check switch cases!");
            }

            /* Old School Way

                if ( vehicle instanceof Motorcycle ) {
                    assertThat(vehicle.getNumberOfWheels()).isEqualTo(3);
                } else if ( )  {
                    assertThat(vehicle.getNumberOfWheels()).isEqualTo(2);
                } else {
                    fail("This type of motorVehicle was not taking into account. Check switch cases!");
                }

             */

        }

        private static Stream<Arguments> provideParameters() {
            return Stream.of(
                    Arguments.of(new Ebike()),
                    Arguments.of(new Motorcycle()));
        }

        @DisplayName("Check Bike Number of Wheels")
        @Test
        void checkBikeNumberOfWheels()
        {
            assertThat(new Bike().getNumberOfWheels()).isEqualTo(BIKE_MOTORCYCLE_WHEELS);
        }

    }

    @DisplayName("JEP 356 - Enhanced pseudo-random number generator")
    @Nested
    class NumberGenerator {

        public static final Integer LOWER_BOUND = 0;
        public static final Integer UPPER_BOUND = 8;
        public static final Integer NUMBER_OF_VALUES_TO_GENERATE = 10;
        public static final Integer RANDOM_SEED = 666;

        @Disabled
        @DisplayName("List all PRNGs names")
        @Test
        void showAllPseudoRandomAlgorithms()
        {
            RandomGeneratorFactory.all()
                    .map(fac -> fac.group()+":"+fac.name())
                    .sorted().forEach(logger::info);
        }

        @DisplayName("All random values are bounded by 8")
        @Test
        void generatedValuesAreUpperBounded()
        {
            /* Legacy way
                RandomGeneratorFactory.of("Random").create(666);

                Default L32X64MixRandom
                RandomGenerator randomGenerator = RandomGeneratorFactory.getDefault().create();
             */

            RandomGenerator randomGenerator = RandomGeneratorFactory.of("L64X128MixRandom").create(RANDOM_SEED);

            Assertions.assertAll(
                    () -> assertThat(IntStream.generate(() ->   randomGenerator.nextInt(UPPER_BOUND))
                                                                .limit(NUMBER_OF_VALUES_TO_GENERATE)
                                                                .allMatch(x -> x < UPPER_BOUND))
                                                                .isEqualTo(true),

                    // The same using ints( ) method to obtain a stream of random values
                    () -> assertThat(randomGenerator.ints(NUMBER_OF_VALUES_TO_GENERATE, LOWER_BOUND, UPPER_BOUND )
                                        .allMatch(x -> x < UPPER_BOUND))
                                        .isEqualTo(true)
                    );

        }


    }

    @DisplayName("JEP 306 - Restore strict floating point semantics")
    @Nested
    class StrictFloatingPointSemantics {

        public static final Double INITIAL_VALUE = 0.1D;
        public static final Integer OPERATOR_VALUE = 3;
        public static final Double EXPECTED_VALUE = 0.3D;

        @Disabled
        @DisplayName("Double Floating Point Problem")
        @Test
        void doubleFailingTest()
        {
            assertThat( INITIAL_VALUE * OPERATOR_VALUE ).isEqualTo(EXPECTED_VALUE);
        }

        @DisplayName("BigDecimal to the rescue")
        @Test
        void usingBigDecimal()
        {
            assertThat(BigDecimal.valueOf(INITIAL_VALUE).multiply(BigDecimal.valueOf(OPERATOR_VALUE)).doubleValue() ).isEqualTo(EXPECTED_VALUE);
        }


    }

}
