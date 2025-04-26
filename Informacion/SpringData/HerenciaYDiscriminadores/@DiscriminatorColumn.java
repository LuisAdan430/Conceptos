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


// * GET ALL
/*
    * Cuando haces un getAll() de la entidad base (Empleado), JPA te devuelve una lista de
    * instancias que pueden ser de cualquiera de las clases hijas, como Gerente,
    * Desarrollador, etc. Esto funciona gracias al discriminador, que le indica a Hibernate qué
    * tipo de objeto instanciar para cada fila.
    * Ejemplo
    * Supón que haces esto con un JpaRepository:
*/
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
}
// * Y luego:
List<Empleado> empleados = empleadoRepository.findAll();
for (Empleado emp : empleados) {
    if (emp instanceof Gerente) {
        System.out.println("Es un gerente: " + ((Gerente) emp).getDepartamento());
    } else if (emp instanceof Desarrollador) {
        System.out.println("Es un dev: " + ((Desarrollador) emp).getLenguajeFavorito());
    }
}

/*
    * JPA automáticamente instancia Gerente o Desarrollador según el valor de la columna tipo_empleado.
    * En resumen:
    * Solo hay una tabla.
    * Hibernate lee el valor del discriminador.
    * Hibernate instancia la clase correspondiente.
    * Puedes tratar todo como Empleado, pero castearlo según necesidad.
*/
// * ¿ Que sucede si se instancia y no se coloca la anotacion?

/*
    * Si creas una clase hija de Empleado pero no le pones la anotación @DiscriminatorValue,
    * Hibernate la sigue mapeando, pero con un comportamiento importante:
    * ¿Qué sucede?
    * Hibernate usará automáticamente el nombre de la clase como el valor del discriminador (tipo_empleado).
    * Es decir, si tu clase se llama Tester y no le pones @DiscriminatorValue("TESTER"), Hibernate asumirá que el valor tipo_empleado = "Tester" corresponde a esa clase.
    * Ejemplo
*/
@Entity
public class Tester extends Empleado {
    private String herramientaTest;
}
// * No usas @DiscriminatorValue.
// * Entonces, si insertas un objeto de tipo Tester:
Tester t = new Tester();
t.setNombre("Carlos");
t.setHerramientaTest("Selenium");
empleadoRepository.save(t);

// * Hibernate guardará algo como:
// * id | nombre  | tipo_empleado | herramientaTest
// *  1 | Carlos  | Tester        | Selenium
/*
    * Y cuando hagas empleadoRepository.findAll(), Hibernate:
    * Verá tipo_empleado = "Tester",
    * Buscará una clase hija de Empleado que se llame "Tester",
    * Y devolverá una instancia de esa clase correctamente.
    * Conclusión
    * No es obligatorio usar @DiscriminatorValue, pero es recomendable para mayor control, claridad y para evitar problemas si cambias el nombre de la clase.
    * Si no lo usas, Hibernate usa el nombre de la clase como valor por defecto.
*/
