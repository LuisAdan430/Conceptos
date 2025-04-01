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
/*
    TODO: Informacion de IA Deepseek
    & La anotación @Repository es una de las anotaciones clave en Spring y Spring Data, utilizada para marcar
    & una clase como un componente de acceso a datos (DAO - Data Access Object). A continuación, te proporciono toda la información relevante sobre esta etiqueta:
    & Definición y Propósito
    & @Repository es una anotación de Spring (org.springframework.stereotype.Repository) que indica que una clase es un repositorio, es decir,
    & un componente encargado de interactuar con una capa de persistencia (base de datos, API externa, etc.).
    & Forma parte de las anotaciones estereotipo de Spring (junto con @Service, @Controller, etc.).
    & Su principal función es:
    & Identificar la clase como un bean de Spring (para que sea gestionada por el contenedor de IoC).
    & Proporcionar traducción automática de excepciones (por ejemplo, convierte excepciones específicas de JPA/Hibernate en excepciones unchecked de Spring DataAccessException).
    & Uso en Spring Data
    & En Spring Data (JPA, MongoDB, etc.), @Repository se usa comúnmente en interfaces que extienden repositorios de Spring Data, aunque no es obligatorio 
    & porque Spring Data ya las detecta automáticamente. Ejemplo:
    & Ejemplo con JPA
*/
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //* Opcional en Spring Data JPA (ya se hereda de JpaRepository)
public interface UserRepository extends JpaRepository<User, Long> {
    //*  Métodos personalizados o heredados (save(), findAll(), etc.)
}
/*
    TODO: Ejemplo con una implementación manual
*/
@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE id = ?", new UserRowMapper(), id);
    }
}
/*
    * Beneficios Clave
    * Traducción de Excepciones
    * Spring convierte excepciones específicas de tecnologías de persistencia (como PersistenceException en JPA o SQLException en JDBC) 
    * en excepciones propias de Spring (DataAccessException), que son unchecked (no requieren try-catch).
    * Integración con Spring
    * Al marcarla con @Repository, la clase se registra como un bean en el contexto de Spring y puede ser inyectada con @Autowired.
    * Claridad Semántica
    * Indica claramente que la clase/interfaz es parte de la capa de persistencia.
    * Diferencia con @Component
    * @Repository es una especialización de @Component (ambas registran un bean en Spring).
    * La diferencia clave es que @Repository** habilita la **traducción automática de excepciones**, mientras que @Component` no.
    * ¿Es Obligatoria en Spring Data?
    * No, en repositorios que extienden JpaRepository, MongoRepository, etc., Spring Data ya los detecta sin necesidad de @Repository.
    * Sí es necesaria si:
    * Implementas un repositorio manualmente (sin usar Spring Data).
    * Quieres garantizar que Spring traduzca las excepciones.
    * Ejemplo de Traducción de Excepciones
    * Si ocurre un error en JPA (como una violación de constraint), Spring convierte:
*/
try {
    userRepository.save(user);
} catch (DataIntegrityViolationException ex) {
    //?  Excepción traducida por Spring (originalmente podría ser SQLException o PersistenceException)
}
/*
    TODO: Configuración Adicional
    TODO: Si usas escaneo de componentes, asegúrate de que el paquete del repositorio esté incluido:
*/

@SpringBootApplication
@EnableJpaRepositories("com.example.repository") // * Para Spring Data JPA
@ComponentScan("com.example")
public class MyApp { ... }

/*
    TODO: Resumen
    TODO: Aspecto                       Detalle
    TODO: Paquete                       org.springframework.stereotype.Repository
    TODO: Propósito                     Marcar un componente como repositorio (acceso a datos).
    TODO: Traducción de excepciones     Convierte excepciones de persistencia en DataAccessException.
    TODO: Alternativa                   @Component (sin traducción de excepciones).
    TODO: Uso típico                    Interfaces de Spring Data o implementaciones manuales de DAOs.

    ! Buenas Prácticas
    ! Úsala en implementaciones manuales de DAOs (no siempre es necesaria en Spring Data JPA/MongoDB).
    ! Combínala con @Transactional para operaciones de escritura.
    ! Evita lógica de negocio en clases con @Repository (de eso se encarga @Service).

*/