/*
    ^ En el contexto de Spring, la anotación @Bean se usa para definir un método dentro de una clase de 
    ^ configuración que devuelve un objeto gestionado por el contenedor de Spring.
    ^ ¿De dónde proviene?
    ^ La anotación @Bean proviene del paquete:
*/
import org.springframework.context.annotation.Bean;
/*
    ^ Forma parte del Spring Framework y se usa en combinación con @Configuration.
    ^ ¿A qué tipo de etiquetas pertenece?
    ^ @Bean pertenece a las anotaciones de configuración de Spring, específicamente dentro de la
    ^ Programación basada en Java (Java-based Configuration).
    ^ Otras anotaciones similares incluyen:
    ^ @Configuration → Indica que la clase define uno o más beans.
    ^ @Component, @Service, @Repository, @Controller → También crean beans automáticamente, pero mediante escaneo de componentes (@ComponentScan).
    ^ @Primary, @Qualifier → Para definir cuál bean se debe usar cuando hay múltiples opciones.
*/
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MiServicio miServicio() {
        return new MiServicio();
    }
}

/*
    ^  En este caso, miServicio() es un método que devuelve una instancia de MiServicio, y Spring lo registrará como un bean en su contexto.
    &  ¿Qué es un Bean en Spring?
    ^  En Spring, un bean es un objeto administrado por el contenedor de Spring. Estos objetos son
    ^  instancias de clases que Spring gestiona, inyecta y proporciona en el ciclo de vida de la aplicación.
    &  Características de un Bean
    ^  Gestionado por Spring → Spring se encarga de crearlo, configurarlo y destruirlo.
    ^  Puede ser inyectado → Se usa en otros componentes mediante inyección de dependencias (DI).
    ^  Tiene un ciclo de vida → Se puede personalizar su inicialización y destrucción.
    & ¿Cómo se crea un Bean en Spring?
    ^  Con la anotación @Component y sus derivados
    ^  Spring detecta y registra automáticamente los beans si las clases están dentro de un paquete escaneado.
*/
import org.springframework.stereotype.Component;

@Component
public class MiServicio {
    public void ejecutar() {
        System.out.println("Ejecutando servicio...");
    }
}

/*
    ^  Variantes de @Component:
    ^  @Service → Para servicios. 
    ^  @Repository → Para clases de acceso a datos.
    ^  @Controller → Para controladores en Spring MVC.
    ^  Con la anotación @Bean en una clase de configuración
    ^  Cuando se necesita más control, se usa @Bean en una clase con @Configuration:
*/
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MiServicio miServicio() {
        return new MiServicio();
    }
}
/*
    ^ ¿Cómo se usa un Bean en Spring?
    ^ Inyección con @Autowired
*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteServicio {

    @Autowired
    private MiServicio miServicio;

    public void procesar() {
        miServicio.ejecutar();
    }
}
/*
    ^  Spring inyecta automáticamente MiServicio en ClienteServicio.
    ^  Uso con el ApplicationContext
*/
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        
        MiServicio servicio = context.getBean(MiServicio.class);
        servicio.ejecutar();
    }
}
/*
    *  ¿Cuál es el ciclo de vida de un Bean en Spring?
    *  Creación → Spring instancia el objeto.
    *  Inicialización → Se ejecutan métodos @PostConstruct o de configuración.
    *  Uso → El bean está disponible en la aplicación.
    *  Destrucción → Se ejecutan métodos @PreDestroy antes de eliminar el bean.
*/

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class MiServicio {

    @PostConstruct
    public void iniciar() {
        System.out.println("Inicializando bean...");
    }

    @PreDestroy
    public void destruir() {
        System.out.println("Destruyendo bean...");
    }
}
/*
    *  ¿Cuándo usar @Bean vs @Component?
    *  Característica	            @Bean en @Configuration     @Component
    *  Definir beans externos	          ✅ Sí	                ❌ No
    *  Personalización avanzada	          ✅ Sí	                ❌ No
    *  Escaneo automático	              ❌ No	                ✅ Sí
    *  Uso en librerías externas	      ✅ Sí	                ❌ No
    * 
    * Si el bean proviene de una clase externa o de una librería, usa @Bean.
    * Si es una clase propia dentro del proyecto, usa @Component.
    * Un bean en Spring es cualquier objeto gestionado por el contenedor, usado para la inyección de
    * dependencias y organizado mediante anotaciones como @Component o @Bean.
    * Limitaciones de component contra las de bean
    ^ Escenario: Necesitamos crear un bean que dependa de un valor dinámico
    ^ Limitación de @Component: No podemos pasar parámetros personalizados en el constructor.
    ^ Ventaja de @Bean: Podemos personalizar su creación con valores dinámicos.
    ^ Ejemplo con @Component (NO permite personalización dinámica)
*/
import org.springframework.stereotype.Component;

@Component
public class MiServicio {
    private String mensaje;

    public MiServicio() {
        this.mensaje = "Mensaje por defecto";  // * No podemos cambiarlo dinámicamente
    }

    public void imprimirMensaje() {
        System.out.println(mensaje);
    }
}

/*
    ^  Problema:
    ^  No podemos pasar valores personalizados (como configuraciones de un archivo de propiedades o valores de inicialización dinámicos).
    ^  Spring crea el bean automáticamente, pero no podemos modificar cómo se instancia.
    ^  Ejemplo con @Bean (Permite personalización dinámica)
*/
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MiServicio miServicio() {
        return new MiServicio("Mensaje personalizado desde @Bean");
    }
}
/*
    ^ Ventaja
    ^ Ahora podemos personalizar la instancia y pasar parámetros dinámicos.
    ^ Comparación en uso dentro del Main
    ^ Con @Component (No configurable)
*/
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("mi.paquete");
        MiServicio servicio = context.getBean(MiServicio.class);
        servicio.imprimirMensaje();  // *  Siempre imprimirá "Mensaje por defecto"
    }
}
/*  
    * Con @Bean (Configuración flexible)
*/
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        MiServicio servicio = context.getBean(MiServicio.class);
        servicio.imprimirMensaje();  //* Imprime "Mensaje personalizado desde @Bean"
    }
}
