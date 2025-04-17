/*
    * La anotación @Enumerated en Java se utiliza en JPA (Java Persistence API) para indicar
    * cómo se debe persistir una enumeración (enum) en la base de datos. Por defecto, JPA no
    * sabe cómo almacenar un valor de tipo enum, por lo que esta anotación le dice si debe
    * guardar el nombre del enum (String) o su posición ordinal (int).
    * Sintaxis
*/
@Enumerated(EnumType.STRING) // *  o EnumType.ORDINAL
private TipoEstado estado;

/*
    * Tipos de EnumType
    * Tipo                  Descripción
    * EnumType.ORDINAL      Guarda la posición del enum (0, 1, 2, ...) en la base de datos.
    * EnumType.STRING       Guarda el nombre del enum tal como aparece en el código (e.g., "ACTIVO").
    * Ejemplo de uso
    * Supongamos que tienes un enum:
*/
public enum Estado {
    ACTIVO,
    INACTIVO,
    PENDIENTE
}
// * Y una entidad:
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    // *  getters y setters
}

/*
    * Esto guardará en la columna estado valores como "ACTIVO", "INACTIVO" o "PENDIENTE".
    * Consideraciones
    * EnumType.STRING es más seguro si se modifica el orden del enum en el código.
    * EnumType.ORDINAL usa menos espacio, pero puede causar errores si se reordena el enum más adelante.
*/