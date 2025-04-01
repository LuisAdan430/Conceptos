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
    *  La etiqueta @Repository no se menciona directamente en los resultados de búsqueda proporcionados, pero parece estar relacionada con Java y Spring Framework.
    *  A continuación, te proporciono información general sobre esta etiqueta en el contexto de Spring.
    *  Información sobre @Repository
    *  La anotación @Repository es una de las anotaciones de Spring Framework utilizada para marcar clases que encapsulan la lógica de acceso a datos.
    *  Esta anotación es parte del paquete org.springframework.stereotype y se utiliza para identificar clases que actúan como capas de acceso a datos, como DAOs (Data Access Objects).
    *  Propósito
    *  El propósito principal de @Repository es:
    *  Identificar clases de acceso a datos: Ayuda a Spring a identificar automáticamente estas clases durante el escaneo de componentes, lo que permite inyectar dependencias automáticamente.
    *  Manejo de excepciones: Spring proporciona un mecanismo para traducir excepciones de acceso a datos a una capa más abstracta y manejable, 
    *  lo que facilita el manejo de errores en la capa de datos.
    *  Uso
    *  Para usar @Repository, simplemente anota la clase que contiene la lógica de acceso a datos con esta anotación. Por ejemplo:
*/
@Repository
public class UsuarioDAO {
    //* Implementación de métodos para acceso a datos
}
/*
    ^ Beneficios
    ^ Inyección automática de dependencias: Spring puede inyectar automáticamente instancias de clases anotadas con @Repository en otras clases que las necesiten.

    ^ Capa de abstracción para excepciones: Ayuda a manejar excepciones de acceso a datos de manera más robusta.

    ^ Consideraciones
    ^ No es obligatorio: Aunque es útil para identificar clases de acceso a datos, no es obligatorio usar @Repository si no se necesita el manejo automático de excepciones o si se prefiere otro enfoque para la inyección de dependencias.

    ^ Compatibilidad con otras anotaciones: Puede usarse junto con otras anotaciones como @Component, @Service, o @Controller, dependiendo del contexto y la arquitectura del proyecto.
*/