/*
    * La anotación @Lob en Spring (más precisamente en JPA — Java Persistence API) se utiliza
    * para mapear atributos de una entidad a tipos de datos grandes en la base de datos, como:
    * BLOB (Binary Large Object): para almacenar datos binarios como imágenes, archivos, etc.
    * CLOB (Character Large Object): para almacenar texto muy largo, como documentos grandes.
    * Uso típico de @Lob
*/
import jakarta.persistence.*;

@Entity
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Lob
    private String contenidoLargo; // * Se mapea a un CLOB

    @Lob
    private byte[] archivo; // * Se mapea a un BLOB

    // * Getters y setters...
}

/*
    * ¿Cómo decide JPA entre CLOB y BLOB?
    * Depende del tipo de dato Java:
    * Si es String o char[] → CLOB
    * Si es byte[] o Serializable → BLOB
    * Consideraciones importantes
    * Algunos motores de base de datos requieren configuraciones específicas para manejar LOBs correctamente.
    * Si vas a usar @Lob con campos grandes, considera usar @Basic(fetch = FetchType.LAZY) 
    * para cargar el dato solo cuando se necesite:
*/

@Lob
@Basic(fetch = FetchType.LAZY)
private String contenidoLargo;

/*
    * Esto es útil para no afectar el rendimiento al traer entidades con campos grandes
    * innecesariamente.
*/