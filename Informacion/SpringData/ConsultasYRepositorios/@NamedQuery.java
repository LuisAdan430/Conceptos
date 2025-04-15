/*
    * La anotación @NamedQuery en Java se utiliza con JPA (Java Persistence API) para definir consultas para
    * definir consultas JPQL (Java Persistence Query Language) con nombre, que pueden reutilizarse en
    * diferentes partes del código.
    * ¿Qué es @NamedQuery?
    * Es una consulta predefinida y nombrada que se asocia a una entidad. Permite que la consulta se defina una
    * sola vez (por ejemplo, cerca de la clase de entidad) y se reutilice fácilmente en el resto de la aplicación.
    * Sintaxis básica
*/
@Entity
@NamedQuery(
    name = "Usuario.buscarPorNombre",
    query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre"
)
public class Usuario {
    @Id
    private Long id;
    private String nombre;
    // * getters y setters
}
/*
    * Múltiples consultas
    * Si necesitas definir varias @NamedQuery en una entidad, usa la anotación @NamedQueries:
*/
@Entity
@NamedQueries({
    @NamedQuery(name = "Usuario.buscarTodos", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.buscarPorEmail", query = "SELECT u FROM Usuario u WHERE u.email = :email")
})
public class Usuario {
    // * campos, getters, setters
}
/*
    * Ventajas
    * Reutilización: Puedes usar la misma consulta en varios lugares sin repetirla.
    * Más legible: Se asocian a las entidades, por lo que son fáciles de ubicar.
    * Rendimiento: Algunas implementaciones pueden precompilarlas para mejorar la ejecución.
    * Cómo usarla en el código
*/
TypedQuery<Usuario> query = em.createNamedQuery("Usuario.buscarPorNombre", Usuario.class);
query.setParameter("nombre", "Juan");
List<Usuario> resultados = query.getResultList();
