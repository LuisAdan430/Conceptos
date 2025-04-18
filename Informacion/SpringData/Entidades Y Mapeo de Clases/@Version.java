/*
    * La anotación @Version en Java (usualmente en el contexto de JPA — Java Persistence API)
    * se utiliza para implementar el control de versiones optimista (optimistic locking) en una
    * entidad. Esta anotación permite que múltiples usuarios trabajen con los mismos datos en
    * paralelo sin sobrescribir accidentalmente los cambios de otro.
    * ¿Cómo funciona @Version?
    * Cuando una entidad tiene un campo anotado con @Version, cada vez que se actualiza esa
    * entidad en la base de datos, el valor del campo de versión se incrementa
    * automáticamente por el proveedor de JPA (como Hibernate).
    * Antes de realizar una actualización, JPA verifica que el valor de la versión no haya
    * cambiado desde que se cargó la entidad. Si el valor ha cambiado, se lanza una excepción
    * (OptimisticLockException) y la actualización no se realiza.
    * Ejemplo básico:
*/
import jakarta.persistence.*;

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private int stock;

    @Version
    private int version;

    // *  Getters y setters
}

/*
    * ¿Qué tipos de datos se pueden usar con @Version?
    * int, Integer
    * long, Long
    * short, Short
    * Timestamp (en este caso, se usa una marca de tiempo)
    * ¿Por qué usar @Version?
    * Para evitar sobrescritura de datos cuando varios usuarios modifican la misma entidad simultáneamente.
    * Para detectar conflictos de concurrencia.
    * Es una alternativa ligera al locking pesimista (que bloquea registros en la base de datos).
    * ¿Qué pasa si hay un conflicto?
    * Supongamos que dos usuarios cargan el mismo producto y lo modifican:
    * El usuario A guarda primero → se incrementa la versión de 1 → 2.
    * El usuario B intenta guardar, pero su versión local sigue siendo 1, y como ya es 2 en la base de datos, JPA lanza
*/
javax.persistence.OptimisticLockException
