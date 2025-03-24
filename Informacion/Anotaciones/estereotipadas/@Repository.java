/*
    * @Repository
    * Especialización de @Component.
    * Se usa para indicar que una clase maneja la capa de acceso a datos.
    * Habilita la conversión automática de excepciones específicas de la base de datos a DataAccessException.
*/

@Repository
public class ClienteRepository {
    public String buscarPorId(Long id) {
        return "Cliente " + id;
    }
}
