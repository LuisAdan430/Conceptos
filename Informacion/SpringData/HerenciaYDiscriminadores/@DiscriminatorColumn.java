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

/*
    * ¿ Es posible colocar algo para que la anotacion no tome la clase heredada ? 
    * Sí, es posible excluir una subclase heredada de los resultados cuando haces un getAll()
    * de la clase base, pero no directamente con una anotación. No existe una anotación tipo
    * @ExcludeFromDiscriminator o algo así en JPA. Sin embargo, hay formas de lograrlo:
    * OPCIONES DISPONIBLES
    * Filtrar en la consulta manualmente
    * Puedes usar una consulta JPQL que excluya el tipo del discriminador que no quieres:
*/
@Query("SELECT e FROM Empleado e WHERE TYPE(e) <> Tester")
List<Empleado> findAllExceptTester();

// * También podrías usar el valor del discriminador:

@Query("SELECT e FROM Empleado e WHERE e.class != Tester")
List<Empleado> findAllExceptTester();

// * O si estás usando @DiscriminatorColumn(name = "tipo_empleado") con valores tipo STRING, puedes filtrar así:

@Query("SELECT e FROM Empleado e WHERE TYPE(e) NOT IN (Tester)")
List<Empleado> findOnlyGerentesYDevs();


/*
    *  Crear una subinterfaz del repositorio con restricciones
    *  Si no quieres que la clase Tester siquiera aparezca en la capa de datos, podrías separar los repositorios:
*/

public interface EmpleadoSinTestersRepository extends JpaRepository<Empleado, Long> {
    @Query("SELECT e FROM Empleado e WHERE TYPE(e) <> Tester")
    List<Empleado> findAllSinTesters();
}

/*
    * ¿Existe una anotación para excluir subclases?
    * No. JPA no proporciona una anotación como:
*/
@ExcludeFromBaseGetAll
public class Tester extends Empleado { ... }

/*
    * Así que si necesitas excluir una clase, lo tienes que hacer a nivel de lógica de consulta.
    * Alternativa "hacker": usar @Where
    * Si usas Hibernate (no estándar JPA), puedes usar @Where:
*/

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_empleado")
@Where(clause = "tipo_empleado != 'Tester'")
public class Empleado {
   ...
}

// * Esto filtrará automáticamente todas las consultas sobre Empleado para excluir a Tester. Pero ⚠️ ojo: también afectará findById, count(), etc.

/*
    * Informacion Detallada de la property DistriminatorType = DiscriminatorType.STRING
    * discriminatorType = DiscriminatorType.STRING
    * Esta parte de la anotación @DiscriminatorColumn le dice a JPA/Hibernate qué tipo de
    * dato se usará en la columna del discriminador (es decir, qué tipo de dato tendrá la
    * columna que diferencia entre las subclases).
    * Opciones disponibles
    * La enumeración DiscriminatorType tiene tres posibles valores:
    * Tipo	                        Descripción
    * DiscriminatorType.STRING	    (Por defecto) Usa texto (cadena de caracteres). Ideal para claridad y legibilidad.
    * DiscriminatorType.CHAR	    Usa un solo carácter como discriminador.
    * DiscriminatorType.INTEGER	    Usa un número entero para diferenciar los tipos.
    * Ejemplo usando STRING
*/
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_empleado", discriminatorType = DiscriminatorType.STRING)
public abstract class Empleado {
    ...
}
@Entity
@DiscriminatorValue("GERENTE")
public class Gerente extends Empleado {
    ...
}
@Entity
@DiscriminatorValue("DEV")
public class Desarrollador extends Empleado {
    ...
}

/*
    * Esto generará una tabla como:
    * id	    nombre	    tipo_empleado	    departamento	    lenguajeFavorito
    * 1	        Ana	        GERENTE	            Finanzas	        NULL
    * 2	        Lucas	    DEV	                NULL	            Java
    * ¿Por qué usar STRING?
    * Más legible cuando inspeccionas la base de datos.
    * Más explícito: sabes qué clase representa cada fila.
    * Mejor para mantenimiento: si cambias nombres de clase, no se rompe nada si mantienes el @DiscriminatorValue.
    * ¿Y si no especificas discriminatorType?
    * Por defecto es como si escribieras:
*/
@DiscriminatorColumn(name = "tipo_empleado", discriminatorType = DiscriminatorType.STRING)

/*
    * Comparativa rápida
    * DiscriminatorType	            Ejemplo de valor	        Ventajas	        Desventajas
    * STRING	                    "GERENTE"	                Legible, claro	    uede ocupar más espacio
    * CHAR	                        'G'                     	Ahorra espacio	    Poco legible, limitado
    * INTEGER	                    1	                        Muy compacto	    Requiere mapa mental / código
*/

/*
    * Informaciomn de la anotacion DiscriminatorType.INTEGER
    * Escenario: Herencia con DiscriminatorType.INTEGER
    * Vamos a modelar una jerarquía de empleados con tres tipos: Empleado (base), Gerente y
    * Desarrollador. Esta vez usaremos números enteros para diferenciarlos.
    * Entidad base
*/
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_empleado", discriminatorType = DiscriminatorType.INTEGER)
public abstract class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
}
// * Subclase: Desarrollador

@Entity
@DiscriminatorValue("2")
public class Desarrollador extends Empleado {
    private String lenguajeFavorito;
}

/*
    * Resultado en base de datos
    * La tabla empleado quedará así:
    * id	            nombre	            tipo_empleado	            departamento	            lenguajeFavorito
    * 1	                Ana	                1	                        Finanzas	                NULL
    * 2	                Leo	                2	                        NULL	                    Java
    * Aquí tipo_empleado = 1 significa Gerente, y 2 significa Desarrollador.
    * ¿Cuándo usar INTEGER?
    * Ventajas
    * Menor espacio (útil en tablas muy grandes).
    * Puede ser más rápido en índices y búsquedas.
    * Desventajas
    * Poco legible si consultas la tabla directamente.
    * Necesitas tener claro el mapeo entre número y clase.
    * Si olvidas agregar @DiscriminatorValue, no sabrás qué número le tocó.
    * Recomendación
    * Usa DiscriminatorType.STRING si:
    * Priorizas claridad y mantenimiento.
    * Te gusta ver los valores como "GERENTE" o "DEV" en la base de datos.
    * Usa INTEGER si:
    * La tabla es muy grande y buscas optimización.
    * El valor del discriminador está controlado externamente (por ejemplo, mapeado desde otro sistema).
*/