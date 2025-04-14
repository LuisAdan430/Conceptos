/*
    * La anotación @EntityGraph en Java (específicamente en el contexto de JPA con Hibernate
    * o Spring Data JPA) se utiliza para definir una estrategia de "fetching" (carga) de
    * relaciones entre entidades, optimizando las consultas para evitar el problema del N+1 y
    * controlar cómo se recuperan las entidades relacionadas (ya sea con fetch join o utilizando
    * las rutas predeterminadas).
    * ¿Qué es @EntityGraph?
    * @EntityGraph permite definir qué relaciones se deben cargar de forma "eager"
    * (inmediata) al ejecutar una consulta, sin tener que usar JOIN FETCH en JPQL. Es
    * especialmente útil cuando trabajas con Spring Data JPA.
    * ¿Cómo se usa?
    * Uso en método de repositorio (Spring Data JPA)
*/
@EntityGraph(attributePaths = {"autor", "editorial"})
List<Libro> findByTituloContaining(String titulo);
/*
    * En este ejemplo, cuando se ejecuta findByTituloContaining, también se cargarán las
    * relaciones autor y editorial del Libro, usando una sola consulta con joins.
    * Uso con un NamedEntityGraph
    * También puedes definir un grafo nombrado en la entidad:
*/
@Entity
@NamedEntityGraph(
    name = "Libro.conAutorYEditorial",
    attributeNodes = {
        @NamedAttributeNode("autor"),
        @NamedAttributeNode("editorial")
    }
)
public class Libro {
    @ManyToOne
    private Autor autor;

    @ManyToOne
    private Editorial editorial;
}
// * Y luego usarlo en el repositorio:
@EntityGraph(value = "Libro.conAutorYEditorial")
List<Libro> findByCategoria(String categoria);
/*
    * Ventajas
    * Evita el problema del N+1.
    * Más limpio que usar @Query con JOIN FETCH.
    * Reutilizable si usas @NamedEntityGraph.
    * Tipos de @EntityGraph
    * Puedes especificar el tipo de carga usando type:
*/

@EntityGraph(attributePaths = {"autor"}, type = EntityGraph.EntityGraphType.LOAD)
/*
    * FETCH: sobreescribe la estrategia de carga establecida por la anotación @ManyToOne, etc.
    * LOAD: respeta la estrategia de carga definida en la entidad.
    * Conclusión
    * @EntityGraph es una herramienta poderosa para optimizar el rendimiento de consultas
    * en aplicaciones JPA, permitiendo un control detallado sobre cómo se cargan las entidades
    * relacionadas, sin necesidad de escribir consultas manuales.
*/