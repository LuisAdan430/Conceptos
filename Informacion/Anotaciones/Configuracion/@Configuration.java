/*
    * La anotación @Configuration en Spring pertenece al tipo de anotaciones de configuración.
    * Específicamente, forma parte de las anotaciones utilizadas para definir clases de configuración en Spring,
    * que permiten configurar y definir beans programáticamente en lugar de usar archivos XML.
    * Características de @Configuration:
    TODO:  Clase de configuración:
    * Indica que la clase es una fuente de definiciones de beans y configuración para el contenedor de Spring.
    * Estas clases suelen contener métodos anotados con @Bean que definen instancias de objetos gestionados por Spring.
    TODO: Uso con @Bean:
    * Dentro de una clase anotada con @Configuration, puedes usar @Bean para registrar beans en el contexto de Spring.
    TODO: Reemplazo de XML:
    * Las clases de configuración son una alternativa moderna y type-safe a la configuración basada en XML.
    TODO: Soporte para otras anotaciones
    * Puede combinarse con otras anotaciones como @ComponentScan, @Import, @PropertySource,etc., para modularizar y organizar la configuración.
*/
@Configuration
public class AppConfig {

    @Bean
    public MiServicio miServicio() {
        return new MiServicio();
    }

    @Bean
    public MiRepositorio miRepositorio() {
        return new MiRepositorio();
    }
}
/*
    * En este ejemplo:
    * AppConfig es una clase de configuración.
    * Los métodos miServicio() y miRepositorio() están anotados con @Bean, lo que indica que
    * Spring gestionará las instancias devueltas por estos métodos como beans.
    ? Relación con otras anotaciones:
    ^ @Component:
        ^ @Configuration es una anotación especializada que hereda de @Component. Esto significa que
        ^ las clases anotadas con @Configuration también son detectadas durante el escaneo de
        ^ componentes si se usa @ComponentScan.
    
    ^ @Bean:
        ^ Se usa dentro de clases de configuración para definir beans.
    
    ^ @Import:
        ^ Permite importar otras clases de configuración.
    
    ^ @PropertySource: 
        ^ Permite cargar archivos de propiedades externos.

    ! Resumen:
        * @Configuration es una anotación de configuración en Spring que se utiliza para definir clases que
        * contienen la configuración de la aplicación, especialmente la definición de beans. Es una parte
        * fundamental del enfoque de configuración basada en Java en Spring, y es una alternativa moderna y
        * flexible a la configuración basada en XML.

*/
/*
    ^ Informacion Extra
    * Antes de la introducción de las anotaciones como @Configuration y @Bean en Spring, la configuración
    * de la aplicación se realizaba principalmente mediante archivos XML. Estos archivos definían los beans y sus
    * dependencias, y el contenedor de Spring los cargaba para gestionar el ciclo de vida de los objetos.
    ! Ejemplo de configuración con XML
    * Supongamos que tienes las siguientes clases:

*/
public class MiServicio {
    private MiRepositorio miRepositorio;

    // *  Constructor para inyección de dependencias
    public MiServicio(MiRepositorio miRepositorio) {
        this.miRepositorio = miRepositorio;
    }

    public void ejecutar() {
        System.out.println("Ejecutando servicio...");
        miRepositorio.guardar();
    }
}

public class MiRepositorio {
    public void guardar() {
        System.out.println("Guardando datos...");
    }
}

/*
    * Para configurar estas clases en Spring usando XML, crearías un archivo de configuración como
    * applicationContext.xml
*/
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
           http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!-- Definición del bean MiRepositorio -->
    <bean id="miRepositorio" class="com.ejemplo.MiRepositorio" />

    <!-- Definición del bean MiServicio con inyección de dependencias -->
    <bean id="miServicio" class="com.ejemplo.MiServicio">
        <constructor-arg ref="miRepositorio" />
    </bean>

</beans>

/*
    * Explicación del XML:
    ^ <beans>:
    * Es el elemento raíz del archivo de configuración. Contiene todas las definiciones de beans.
    ^ <bean>:
    * Define un bean. El atributo id es el nombre del bean, y el atributo class especifica la clase que se instanciará
    ^ <constructor-arg>:
    * Se usa para inyectar dependencias a través del constructor. En este caso, el bean miRepositorio se inyecta en el constructor de MiServicio. 
    & Cargar la configuración XML en una aplicación Spring:
    * Para cargar el archivo XML y obtener los beans definidos, puedes usar ClassPathXmlApplicationContext:
*/
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Aplicacion {
    public static void main(String[] args) {
        //  ^ Cargar el archivo de configuración XML
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        //  ^ Obtener el bean MiServicio
        MiServicio servicio = context.getBean(MiServicio.class);
        servicio.ejecutar();
    }
}

/*
    &  Comparación con la configuración basada en Java:
    * El mismo ejemplo usando anotaciones (@Configuration y @Bean) sería:
*/ 
@Configuration
public class AppConfig {

    @Bean
    public MiRepositorio miRepositorio() {
        return new MiRepositorio();
    }

    @Bean
    public MiServicio miServicio() {
        return new MiServicio(miRepositorio());
    }
}
/*
    * Y para cargar la configuración:
*/
public class Aplicacion {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        MiServicio servicio = context.getBean(MiServicio.class);
        servicio.ejecutar();
    }
}

/*
    ^ Ventajas de la configuración basada en Java sobre XML:
    ^ Type-safe:
    ^ La configuración basada en Java es type-safe, lo que significa que los errores se detectan en tiempo de compilación en lugar de en tiempo de ejecución
    ^ Facilidad de mantenimiento:
    ^ Es más fácil refactorizar y mantener el código Java que los archivos XML.
    ^ Legibilidad: 
    ^ El código Java es más legible y expresivo que los archivos XML, especialmente para desarrolladores familiarizados con Java.
    ^ Integración con herramientas de desarrollo:
    ^ Las anotaciones y el código Java se integran mejor con IDEs modernas, lo que facilita la navegación y el autocompletado.
    ^ Resumen:
    ^ Antes de las anotaciones, la configuración de Spring se realizaba mediante archivos XML, donde se
    ^ definían los beans y sus dependencias. Aunque esta aproximación sigue siendo válida, la configuración
    ^ basada en Java (usando @Configuration y @Bean) es más moderna, type-safe y fácil de mantener.
*/