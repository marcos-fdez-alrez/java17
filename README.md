# Java 17 New Features

Some Java 17 New Features Examples

## [JEP 306: Restore Always-Strict Floating-Point Semantics](https://openjdk.java.net/jeps/306)


Se han eliminado ciertas variaciones sobre las operaciones de coma flotante introducidas desde Java 1.2 para suplir ciertas limitaciones de las arquitecturas de hardware sobre las que corría la JVM.

Desde Java 1.2 para realizar operaciones flotantes estrictas había que utilizar strictfp que se vuelve con este JEP opcional


## [JEP 356: Enhanced Pseudo-Random Number Generator](https://openjdk.java.net/jeps/356)

Mejoras en el paquete java.util.random para la utilización de distintos PRNGs (Pseudo-Random Number Generators) y facilitar la utilización de diversos algoritmos en lugar de hardcodear la implementación.

Se incluye java.util.random.RandomGeneratorFactory como punto de entrada que permite obtener un método por .of(String name) o listar todos los existentes por all().

Las nuevas implementaciones no son Thread Safe solo java.util.Random and java.security.SecureRandom lo son.


## [JEP 406: Pattern Matching for switch (Preview) ](https://openjdk.java.net/jeps/406)

Desarrollo dentro del proyecto Amber (https://openjdk.java.net/projects/amber/ ) de la posibilidad de evaluar patrones dentro de un switch


## [JEP 409: Sealed Classes or Interfaces ](https://openjdk.java.net/jeps/409)

La introducción de clases e interfaces cerradas (sealed classes/interfaces) permite al desarrollador indicar que clases o interfaces pueden extender o implementar una clase definida como cerrada.

Esta característica ha sido introducida a través del Proyecto Amber (https://openjdk.java.net/projects/amber/ ) cuya finalidad es aumentar la productividad del programador a través de la evolución del lenguaje Java,
en concreto tener una lista exacta de las clases/interfaces que pueden extender/implementar una clase cerrada puede simplificar el Desarrollo.
Se propuso y liberó como preview en Java 15 ( JEP 360 ) y se refine en Java 16 ( JEP 397 ) , en este JEP se ha finalizado en Java 17 sin cambios desde Java 16.

Objetivos:

- Permitir al desarrollador de una clase/interfaz controlar que Código puede implementarlos

- Proporcionar un método más declarative que los modificadores de acceso para restringir el uso de una superclase

Restricciones:

- Las subclases permitidas tienen que estar en el mismo modulo que la sealed class/iface

- Las subclases tienen que extender explícitamente la clase cerrada

- Las subclases tienen que definer un modificador: final, sealed o non-sealed


## To execute use:
```
mvn clean test
```
