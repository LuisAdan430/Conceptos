/*
    * La anotación @DiscriminatorColumn se usa en JPA (Java Persistence API), comúnmente
    * con Hibernate (que es el proveedor de JPA por defecto en Spring Boot). Esta anotación es
    * relevante cuando usas herencia en tus entidades y eliges una estrategia de persistencia
    * llamada SINGLE_TABLE.
    * ¿Qué es @DiscriminatorColumn?
    * @DiscriminatorColumn se usa junto con la anotación @Inheritance(strategy =
    * InheritanceType.SINGLE_TABLE) para indicar qué columna de la tabla (única) distinguirá
    * qué tipo de entidad es cada fila.
    * Detalle de los atributos:
    * Atributo                 Descripción
    * name                     El nombre de la columna que se usará para discriminar el tipo de entidad.
    * discriminatorType        El tipo de datos de la columna (STRING, CHAR, INTEGER). Por defecto es STRING.
    * length                   Longitud de la columna si el tipo es STRING o CHAR.
    * columnDefinition
*/