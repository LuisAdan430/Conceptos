/*
    * ​La anotación @Entity en Java es fundamental en la Java Persistence API (JPA) y en Jakarta
    * Persistence. Se utiliza para indicar que una clase es una entidad persistente, es decir, que
    * sus instancias se almacenarán como registros en una tabla de una base de datos
    * relacional.​
    * ¿Qué es una entidad en JPA?
    * Una entidad es una clase Java que representa una tabla en la base de datos. Cada
    * instancia de la clase corresponde a una fila en dicha tabla. Estas clases suelen ser POJOs
    * (Plain Old Java Objects) y están anotadas con @Entity para que JPA las reconozca como
    * entidades persistentes. Además, deben tener un constructor sin argumentos y una clave
    * primaria definida con la anotación @Id .​
    * Ejemplo básico de uso
*/
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Empleado {
    @Id
    private Long id;
    private String nombre;
    private String puesto;

    public Empleado() {} // * Constructor sin argumentos

    // * Getters y setters
}

/*
    * En este ejemplo, la clase Empleado está anotada con @Entity, lo que indica que es una
    * entidad persistente. El campo id está anotado con @Id, definiéndolo como la clave
    * primaria de la entidad.
    * Consideraciones adicionales
    * Nombre de la tabla: Si no se especifica lo contrario, JPA asume que el nombre de la
    * tabla es el mismo que el de la clase. Para personalizarlo, se puede usar la anotación
    * @Table(name = "nombre_tabla").
    * Requisitos de la clase: La clase debe tener un constructor sin argumentos. Además, ni
    * la clase ni sus atributos persistentes deben ser finales, y es recomendable que los
    * atributos tengan métodos getter y setter públicos .
    * Relaciones entre entidades: JPA permite definir relaciones entre entidades utilizando
    * anotaciones como @OneToMany, @ManyToOne, @OneToOne y @ManyToMany, facilitando
    * el mapeo de relaciones entre tablas en la base de datos .

*/