/*
    * La anotación @Id en Java (específicamente en el contexto de JPA — Java
    * Persistence API) se utiliza para marcar un campo de una entidad como la clave primaria
    * (primary key) de la tabla correspondiente en la base de datos.
    * ¿Dónde se usa?
    * Se usa dentro de una clase anotada con @Entity, que representa una tabla en la base de datos.
    * Ejemplo básico:
*/
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Usuario {

    @Id
    private Long id;

    private String nombre;
    private String email;

    // *  Getters y setters...
}

/*
    * Detalles importantes:
    * Solo un campo puede estar anotado con @Id en una clase entidad (clave primaria compuesta requiere otro enfoque: @EmbeddedId o @IdClass).
    * Puede ser de tipo Long, Integer, String, UUID, etc.
    * JPA necesita saber cuál campo identifica de forma única cada registro.
    * Combinar con otras anotaciones:
    * @GeneratedValue: Para generar el valor automáticamente (por ejemplo, auto-incremental):
*/
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

// * @Column: Para personalizar la columna:
@Id
@Column(name = "usuario_id")
private Long id;

// * Estrategias de generación (@GeneratedValue):
@GeneratedValue(strategy = GenerationType.AUTO)
@GeneratedValue(strategy = GenerationType.IDENTITY)
@GeneratedValue(strategy = GenerationType.SEQUENCE)
@GeneratedValue(strategy = GenerationType.TABLE)
