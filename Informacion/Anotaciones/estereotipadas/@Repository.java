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
/*
    ^ La anotación @Repository en Spring es una especialización de @Component que se usa para marcar las clases de acceso a datos (DAO o Repositories).
    ^ Características de @Repository
    ^ Indica una capa de persistencia
    ^ Se aplica a clases que interactúan con la base de datos, generalmente implementaciones de JpaRepository o CrudRepository.
    ^ Manejo de excepciones
    ^ Spring la usa para la traducción de excepciones de persistencia (por ejemplo, convierte excepciones específicas de JDBC o JPA en DataAccessException).
    ^ Forma parte del escaneo de componentes
    ^ Al ser una especialización de @Component, las clases con @Repository son detectadas automáticamente por Spring en el escaneo de paquetes.
    ^ Ejemplo de Uso
    ^ Si usas Spring Data JPA, puedes marcar una interfaz como @Repository:
*/ 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByNombre(String nombre);
}
/*
    ^ O si defines un DAO manualmente:
*/
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UsuarioDao {
    
    @PersistenceContext
    private EntityManager entityManager;

    public Usuario findById(Long id) {
        return entityManager.find(Usuario.class, id);
    }
}
/*
    ^ Diferencias con otras anotaciones similares
    ^  Anotación	            Propósito	                         Equivalente a @Component	            Manejo de Excepciones
    ^  @Component	            Genérica para cualquier bean                    ✅                                   ❌
    ^  @Service                 Lógica de negocio (Service Layer)               ✅                                   ❌
    ^  @Repository              Capa de persistencia (DAO/Repositorio)          ✅                                   ✅

    *  Nota: Aunque @Repository es opcional con Spring Data JPA, se recomienda usarla para un mejor manejo de excepciones.
*/