/*
    * La anotación @Column en Java se utiliza en JPA (Java Persistence API) para mapear un
    * atributo de una clase entidad a una columna en una tabla de base de datos. Forma parte
    * del paquete javax.persistence o jakarta.persistence (dependiendo de la versión de
    * JPA/Spring que estés usando).
    * Aquí te dejo un resumen con sus elementos más comunes:
    * Importación
*/
import jakarta.persistence.Column; // *  o javax.persistence.Column

// * Uso básico
@Column(name = "nombre_columna")
private String nombre;

/*
    * Atributos más comunes
    * Atributo                      Descripción
    * name                          Especifica el nombre de la columna en la tabla.
    * nullable                      Define si la columna permite valores NULL (por defecto es true).
    * unique                        Indica si la columna debe tener valores únicos.
    * length                        Longitud máxima para columnas de tipo String (por defecto 255).
    * precision                     Número total de dígitos para columnas BigDecimal o numeric.
    * scale                         Número de dígitos a la derecha del punto decimal.
    * insertable                    Si false, el campo no se incluirá en sentencias INSERT.
    * updatable                     Si false, el campo no se incluirá en sentencias UPDATE.
    * columnDefinition              Permite definir directamente el tipo SQL de la columna.
    * Ejemplo completo
*/
@Column(
    name = "precio_unitario",
    nullable = false,
    unique = false,
    precision = 10,
    scale = 2
)
private BigDecimal precioUnitario;

/*
    * Este ejemplo crea una columna precio_unitario que no permite NULL, admite valores
    * con hasta 10 dígitos en total, de los cuales 2 son decimales.
*/