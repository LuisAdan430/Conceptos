/*
    * La anotación @SqlResultSetMapping es parte de JPA (Java Persistence API) y se utiliza para mapear
    * resultados de consultas SQL nativas (@NamedNativeQuery o EntityManager.createNativeQuery) a entidades
    * o estructuras específicas en Java, como entidades JPA, POJOs, o columnas escalares.
    * ¿Para qué se usa?
    * Cuando haces una consulta SQL nativa (es decir, no JPQL), los resultados pueden no coincidir exactamente
    * con una entidad JPA. @SqlResultSetMapping permite indicar cómo deben interpretarse y mapearse esos
    * resultados.
    * Estructura básica
*/
@SqlResultSetMapping(
    name = "TuMappingPersonalizado",
    entities = {
        @EntityResult(
            entityClass = TuEntidad.class,
            fields = {
                @FieldResult(name = "id", column = "id_columna_sql"),
                @FieldResult(name = "nombre", column = "nombre_columna_sql")
            }
        )
    }
)
/*
    * Opciones que puedes usar en @SqlResultSetMapping
    * entities: Mapea los resultados a una o más entidades JPA.
    * columns: Mapea columnas individuales a valores escalares (útil si no se mapea a una entidad).
    * classes: (Desde JPA 2.1) Permite mapear directamente a constructores de clases (constructor result mapping), útil para DTOs.
    *  Ejemplo completo: Mapping a entidad
*/
@SqlResultSetMapping(
    name = "UsuarioMapping",
    entities = @EntityResult(
        entityClass = Usuario.class,
        fields = {
            @FieldResult(name = "id", column = "usuario_id"),
            @FieldResult(name = "nombre", column = "nombre_usuario"),
            @FieldResult(name = "email", column = "correo")
        }
    )
)
// * Y luego usarías el mapping así:
List<Usuario> usuarios = entityManager.createNativeQuery(
    "SELECT usuario_id, nombre_usuario, correo FROM usuarios",
    "UsuarioMapping"
).getResultList();
// * Ejemplo: Mapping a un DTO (constructor mapping)
@SqlResultSetMapping(
    name = "UsuarioDtoMapping",
    classes = @ConstructorResult(
        targetClass = UsuarioDTO.class,
        columns = {
            @ColumnResult(name = "nombre", type = String.class),
            @ColumnResult(name = "correo", type = String.class)
        }
    )
)
// * Luego: 
List<UsuarioDTO> dtos = entityManager.createNativeQuery(
    "SELECT nombre, correo FROM usuarios",
    "UsuarioDtoMapping"
).getResultList();
/*
    * Recomendaciones
    * Usa entities si quieres resultados como entidades JPA completas.
    * Usa classes (@ConstructorResult) si prefieres cargar datos en un DTO directamente.
    * Usa columns (@ColumnResult) si necesitas datos simples o sueltos.
*/