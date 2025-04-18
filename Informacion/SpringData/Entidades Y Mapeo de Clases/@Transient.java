/*
    * La anotación @Transient en Java se utiliza comúnmente en el contexto de la persistencia
    * de datos, especialmente cuando estás trabajando con JPA (Java Persistence API) o
    * frameworks como Hibernate.
    * ¿Qué hace exactamente @Transient?
    * Cuando marcas un campo con @Transient, estás indicando que ese campo no debe ser
    * persistido en la base de datos. Es decir, no será almacenado cuando se guarde la entidad.
    * Ejemplo:
*/
@Entity
public class Producto {
    
    @Id
    private Long id;

    private String nombre;

    @Transient
    private double precioCalculado;

    // * getters y setters
}

/*
    * En este caso, precioCalculado no se guardará en la base de datos, aunque forme parte
    * de la clase.
    * Diferencia con transient (palabra clave de Java)
    * @Transient es parte de JPA/Hibernate y afecta la persistencia en bases de datos.
    * transient es una palabra clave de Java que evita que el campo sea serializado (por ejemplo, cuando se convierte un objeto a un stream para guardarlo en un archivo).
    * Pueden usarse juntas si no quieres ni persistirlo en DB ni serializarlo:
*/

@Transient
private transient String campoTemporal;
