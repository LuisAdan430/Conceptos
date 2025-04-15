/*
    * La anotación @Query en Spring es parte de Spring Data JPA y se utiliza para definir consultas
    * personalizadas en los repositorios, ya sea en JPQL (Java Persistence Query Language) o SQL nativo.
    * ¿Dónde se usa?
    * Se usa dentro de interfaces que extienden JpaRepository o CrudRepository.
*/
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query("SELECT u FROM Usuario u WHERE u.nombre = :nombre")
    List<Usuario> buscarPorNombre(@Param("nombre") String nombre);
}
/*
    * Características principales
    * Elemento                  Descripción
    * value                     Define la consulta JPQL o SQL.
    * nativeQuery               Si es true, la consulta es SQL nativo. Si es false (por defecto), es JPQL.
    * @Param("nombre")          Permite enlazar parámetros con nombre.
    * Ejemplos
    * Consulta JPQL
*/
@Query("SELECT u FROM Usuario u WHERE u.edad > :edad")
List<Usuario> findMayoresDe(@Param("edad") int edad);

// *  Consulta SQL nativa

@Query(value = "SELECT * FROM usuarios WHERE edad > :edad", nativeQuery = true)
List<Usuario> buscarMayoresDe(@Param("edad") int edad);

// * Consultas con múltiples parámetros
@Query("SELECT u FROM Usuario u WHERE u.nombre = :nombre AND u.edad = :edad")
Usuario buscarPorNombreYEdad(@Param("nombre") String nombre, @Param("edad") int edad);


/*
    * Ventajas
    * Mayor control sobre la consulta.
    * Puedes hacer consultas complejas que no se pueden construir fácilmente con los métodos de nombre derivado.
    * Compatible con JPQL y SQL nativo.
*/