/*
    * La anotación @NamedQueries en Java se utiliza dentro del contexto de JPA (Java Persistence API) y sirve para
    * definir múltiples consultas con nombre (named queries) que luego pueden reutilizarse a lo largo del
    * ódigo, evitando tener que escribir las consultas JPQL (Java Persistence Query Language) en varios lugares.
    * ¿Qué es una Named Query?
    * Es una consulta predefinida (generalmente JPQL) que se asocia a una entidad JPA, y tiene un nombre único
    * para ser invocada desde el código.
    * Sintaxis básica
*/
@Entity
@NamedQueries({
    @NamedQuery(
        name = "Usuario.findAll",
        query = "SELECT u FROM Usuario u"
    ),
    @NamedQuery(
        name = "Usuario.findByNombre",
        query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre"
    )
})
public class Usuario {
    // * atributos, getters y setters
}
/* 
    * ¿Cómo se usa?
    * Una vez definida, puedes llamar a la consulta desde el EntityManager así:
*/
TypedQuery<Usuario> query = entityManager.createNamedQuery("Usuario.findByNombre", Usuario.class);
query.setParameter("nombre", "Carlos");
List<Usuario> resultados = query.getResultList();

/*
    * Diferencias entre @NamedQuery y @NamedQueries
    * Anotación                 	Usar
    * @NamedQuery                   Definiruna sola consulta nombrada
    * @NamedQueries                 Agrupa varias consultas nombradas en una sola anotación
    * @NamedQueries es simplemente un contenedor de múltiples @NamedQuery
    * Ventajas
    * Mejor organización del código (las consultas están junto a las entidades).
    * Permite que el proveedor JPA valide las consultas al inicio.
    * Reutilización: puedes invocar la misma consulta en múltiples partes del sistema.
*/