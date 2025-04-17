/*
    * La anotación @GeneratedValue en Java es parte de JPA (Java Persistence API) y se utiliza
    * para indicar que el valor de una columna (típicamente una clave primaria) se generará
    * automáticamente. Esta anotación suele ir junto con @Id, que marca el campo como
    * identificador de la entidad.
    * Sintaxis básica:
*/
@Id
@GeneratedValue
private Long id;
// * Atributos de @GeneratedValue
@GeneratedValue(strategy = GenerationType, generator = "generatorName")

/*
    * Strategy
    * Define la estrategia de generación de valores. Los tipos posibles son:
    * GenerationType.AUTO:
    * La estrategia por defecto. JPA elige automáticamente una estrategia apropiada según
    * el proveedor de persistencia (Hibernate, EclipseLink, etc.).
    * GenerationType.IDENTITY:
    * Utiliza una columna con auto-incremento (como AUTO_INCREMENT en MySQL). No requiere secuencias.
    * GenerationType.SEQUENCE:
    * Utiliza una secuencia en la base de datos. Necesita definir una anotación @SequenceGenerator.
    * GenerationType.TABLE:
    * Usa una tabla especial para mantener y generar valores únicos. Es más lenta pero más compatible con diferentes bases de datos.
    * generator
    * Nombre del generador a usar, si estás usando SEQUENCE o TABLE. Se define con @SequenceGenerator o @TableGenerator.
    * Ejemplo con GenerationType.SEQUENCE:
*/
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq")
    @SequenceGenerator(name = "usuario_seq", sequenceName = "usuario_seq", allocationSize = 1)
    private Long id;

    private String nombre;
}

// * Ejemplo con GenerationType.IDENTITY (muy común en MySQL):
@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
}

/* 
    * ¿Cuándo usar cada estrategia?
    * Estrategia            Uso común
    * AUTO                  Cuando no quieres preocuparte por detalles de la BD.
    * IDENTITY              Ideal para bases como MySQL o SQL Server.
    * SEQUENCE              Ideal para Oracle, PostgreSQL, etc.
    * TABLE                 Para máxima portabilidad (poco usada).
*/