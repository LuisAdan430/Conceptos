/*
    * La anotación @PersistenceContext se utiliza en Java EE (y también en aplicaciones Spring) para inyectar un
    * contexto de persistencia, es decir, una instancia de EntityManager, que se usa para interactuar con la base
    * de datos en aplicaciones JPA (Java Persistence API).

    * ¿Qué hace exactamente @PersistenceContext?
    * Esta anotación indica al contenedor (como un servidor de aplicaciones Java EE o el contexto de Spring) que
    * debe inyectar automáticamente un EntityManager para que pueda ser utilizado en la clase donde se
    * declara.
*/
@PersistenceContext
private EntityManager entityManager;

/*
    * ¿Cómo funciona?
    * Cuando usas @PersistenceContext, el contenedor gestiona el ciclo de vida del EntityManager. Esto significa que:
    * No necesitas cerrarlo manualmente.
    * Está vinculado al contexto de transacción.
    * Está sincronizado automáticamente con la base de datos al final de cada transacción.
    * Atributos comunes
    * unitName: Especifica el nombre de la unidad de persistencia definida en el archivo persistence.xml.
*/
@PersistenceContext(unitName = "miUnidadPersistencia")
private EntityManager em;
/*
    * @PersistenceContext vs @Autowired en Spring
    * @PersistenceContext es más estándar (JPA).
    * En Spring, puedes usar también @Autowired con @Bean de tipo EntityManager, pero
    * @PersistenceContext sigue siendo recomendable para mantener una gestión correcta del contexto
    * transaccional.
    * Ejemplo práctico
*/
@Repository
public class UsuarioRepository {

    @PersistenceContext
    private EntityManager em;

    public Usuario buscarPorId(Long id) {
        return em.find(Usuario.class, id);
    }

    public void guardar(Usuario usuario) {
        em.persist(usuario);
    }
}

/*
    * Resumen
    * Característica                Descripción
    * Propósito                     Inyectar un EntityManager gestionado
    * Usado en                      JPA, Java EE, Spring
    * Vinculado a                   Contexto de persistencia / transacción
    * ¿Cierra el EntityManager?     No (lo hace el contenedor automáticamente)
    * Atributos clave               unitName, type (rara vez usado)

*/