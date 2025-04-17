/*
    * La anotación @Basic en Java es parte de JPA (Java Persistence API) y se
    * utiliza para indicar que un atributo de una entidad debe ser persistido de
    * forma "básica" (es decir, directamente mapeado a una columna de la base
    * de datos sin transformación adicional o relaciones complejas).
    * ¿Dónde se usa?
    * Se aplica a los atributos de una clase de entidad (aquella que está anotada con @Entity), típicamente junto con @Column.
    * Sintaxis:
*/
@Basic(optional = true) // * true es el valor por defecto
@Column(name = "nombre_columna")
private String atributo;

/*
    * Parámetro             Descripción
    * optional              Indica si el atributo puede ser null. Por defecto es true. Si se
    *                       establece como false, JPA espera que ese campo no sea null en
    *                       la base de datos.
    * fetch                 Define el tipo de carga (fetching). Puede ser FetchType.EAGER (por
    *                       defecto) o FetchType.LAZY.
    * Ejemplo con fetch:
*/
@Basic(fetch = FetchType.LAZY)
@Column(name = "descripcion_larga")
private String descripcionLarga;

/*
    * Cuándo es necesaria?
    * En realidad, no es obligatorio usarla explícitamente. Todos los atributos de
    * tipos simples (primitivos, String, Integer, Date, etc.) se tratan como
    * @Basic por defecto si no se especifica otra anotación (@OneToMany,
    * @Embedded, etc.).
    * Se usa explícitamente cuando quieres personalizar los parámetros como
    * optional = false o fetch = FetchType.LAZY.
    * ¿Cuándo no se usa @Basic?
    * No se usa en:
    * Relaciones entre entidades (@OneToMany, @ManyToOne, etc.)
    * Campos embebidos (@Embedded)
    * Campos que no se deben persistir (@Transient)

*/