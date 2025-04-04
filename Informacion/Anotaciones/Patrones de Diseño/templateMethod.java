/*
    * Template Method
    * Patrón: Template Method
    * Descripción: Define el esqueleto de un algoritmo en una superclase y permite a las subclases definir algunos pasos
    * sin cambiar la estructura general.
    * Ejemplo en Spring Boot: Uso de JdbcTemplate
*/
@Repository
public class UsuarioRepositorio {
    private final JdbcTemplate jdbcTemplate;

    public UsuarioRepositorio(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Usuario> obtenerUsuarios() {
        return jdbcTemplate.query("SELECT * FROM usuarios", new UsuarioMapper());
    }
}
