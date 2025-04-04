/*
    *   Adapter
    *   Patrón: Adapter
    *   Descripción: Permite que clases incompatibles trabajen juntas mediante una interfaz intermedia.
    *   Ejemplo en Spring Boot: Uso de JpaRepository
*/

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    // *  JpaRepository actúa como un Adapter entre la BD y nuestro código
}
// * Uso en Spring: JpaRepository, JdbcTemplate, RestTemplate.