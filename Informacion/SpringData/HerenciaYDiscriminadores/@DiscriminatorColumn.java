/*
    * La anotación @DiscriminatorColumn se usa en JPA (Java Persistence API), comúnmente
    * con Hibernate (que es el proveedor de JPA por defecto en Spring Boot). Esta anotación es
    * relevante cuando usas herencia en tus entidades y eliges una estrategia de persistencia
    * llamada SINGLE_TABLE.
    * ¿Qué es @DiscriminatorColumn?
    * @DiscriminatorColumn se usa junto con la anotación @Inheritance(strategy =
    * InheritanceType.SINGLE_TABLE) para indicar qué columna de la tabla (única) distinguirá
    * qué tipo de entidad es cada fila.
    * Detalle de los atributos:
    * Atributo                 Descripción
    ? name                     El nombre de la columna que se usará para discriminar el tipo de entidad.
    ? discriminatorType        El tipo de datos de la columna (STRING, CHAR, INTEGER). Por defecto es STRING.
    * length                   Longitud de la columna si el tipo es STRING o CHAR.
    * columnDefinition         Definición personalizada de la columna SQL.
    * insertable y updatable   Controlan si se puede insertar o actualizar esta columna desde JPA.
    * ¿Cuándo se usa?
    * Cuando modelas herencia en tus entidades y quieres usar una única tabla para almacenar
    * todos los tipos (clases hijas), necesitas alguna forma de diferenciar cuál es cuál. Ahí entra
    * el discriminador.
    * Ejemplo práctico con Spring Boot y JPA
*/
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_empleado", discriminatorType = DiscriminatorType.STRING)
public abstract class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    //* getters y setters
}

@Entity
@DiscriminatorValue("GERENTE")
public class Gerente extends Empleado {
    private String departamento;
}

@Entity
@DiscriminatorValue("DESARROLLADOR")
public class Desarrollador extends Empleado {
    private String lenguajeFavorito;
}

// * Con esto, se creará una única tabla empleado con columnas como:
// * id | nombre | tipo_empleado | departamento | lenguajeFavorito
// * Y JPA usará el valor en tipo_empleado para saber si la fila es un Gerente o un Desarrollador.




