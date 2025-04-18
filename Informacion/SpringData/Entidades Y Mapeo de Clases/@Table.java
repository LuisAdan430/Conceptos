/*
    * La anotación @Table en Java se utiliza junto con la anotación @Entity para mapear una
    * clase de entidad a una tabla específica en una base de datos cuando se trabaja con JPA
    * (Java Persistence API).
    * ¿Dónde se encuentra?
    * Pertenece al paquete:
*/
import jakarta.persistence.Table;
// * o en versiones anteriores:
// * import javax.persistence.Table;
// * Uso Básico
@Entity
@Table(name = "usuarios")
public class Usuario {
    
    @Id
    private Long id;

    private String nombre;
    private String correo;
}

/*
    * En este ejemplo:
    * @Entity: Declara que la clase es una entidad JPA.
    * @Table(name = "usuarios"): Especifica que la entidad Usuario está mapeada a la
    * tabla llamada "usuarios" en la base de datos.
    * Atributos de @Table
    * Atributo                      Descripción
    * name                          Nombre de la tabla en la base de datos.
    * catalog                       Nombre del catálogo (opcional, depende del motor de base de datos).
    * schema                        Nombre del esquema de base de datos (útil para bases con múltiples esquemas).
    * uniqueConstraints             Define restricciones únicas en columnas específicas.
    * indexes                       Permite declarar índices en columnas de la tabla.
    * Ejemplo con restricciones e índices
*/
@Entity
@Table(
    name = "usuarios",
    schema = "public",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"correo"})
    },
    indexes = {
        @Index(name = "idx_usuario_nombre", columnList = "nombre")
    }
)
public class Usuario {
    
    @Id
    private Long id;

    private String nombre;

    private String correo;
}
