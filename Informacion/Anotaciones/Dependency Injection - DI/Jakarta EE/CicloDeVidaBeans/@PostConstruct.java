/*
    ^  La etiqueta @PostConstruct en el contexto de Spring proviene de Jakarta EE (anteriormente Java EE),
    ^  específicamente del paquete jakarta.annotation. Antes estaba en javax.annotation, pero con la
    ^  evolución de Java EE a Jakarta EE, cambió de paquete. 
    ^  ¿A qué categoría pertenece?
    ^  @PostConstruct es una anotación de ciclo de vida que se usa para indicar que un método debe
    ^  ejecutarse automáticamente después de que el bean haya sido instanciado e inicializado por el
    ^  contenedor de Spring.
    ^  ¿Cuándo se usa?
    ^  Se utiliza principalmente en los siguientes casos:
    ^  Inicialización de Beans: Ejecutar código después de la inyección de dependencias,   
    ^  como configurar valores predeterminados o realizar validaciones.

    ^  Cargar datos o configuraciones: Consultar bases de datos, leer archivos de configuración, etc.
    ^  Registro de servicios: Registrar un servicio en un registro distribuido, como Eureka o Consul.
    &  Ejemplo de uso en Spring Boot
*/
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class MyBean {

    @PostConstruct
    public void init() {
        System.out.println("Bean inicializado correctamente.");
    }
}

/*
    ^  Alternativa en Spring Boot 3+
    ^  Desde Spring Boot 3, la anotación @PostConstruct ya no está incluida en el JDK ni en Jakarta EE por
    ^  defecto. Se recomienda usar @EventListener(ApplicationReadyEvent.class) como alternativa:
*/

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MyBean {

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        System.out.println("Bean inicializado después de que la aplicación esté lista.");
    }
}
/*
    ^  Conclusión 
    ^  @PostConstruct proviene de Jakarta EE (jakarta.annotation).
    ^  Es una anotación de ciclo de vida utilizada para ejecutar código después de la inicialización de un bean.
    ^  En Spring Boot 3+, se recomienda usar @EventListener(ApplicationReadyEvent.class) en su lugar.
    ^  Dado que estás trabajando con Spring Boot 3.3.2, la anotación @PostConstruct ya no es la mejor
    ^  opción porque Jakarta EE no está incluida por defecto en Spring Boot 3+.
    ^  Aquí te dejo un ejemplo actualizado y recomendado para inicializar código después de que la aplicación
    ^  esté lista en Spring Boot 3.3.2 usando @EventListener(ApplicationReadyEvent.class).
    !  Ejemplo de inicialización de un Bean en Spring Boot 3.3.2
    *  Supongamos que necesitas cargar datos desde la base de datos o hacer alguna configuración al iniciar la aplicación.
    *  Usando @EventListener (Recomendado en Spring Boot 3+)
*/

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MyBean {

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        System.out.println("Aplicación lista: ejecutando inicialización...");
        // * Lógica de inicialización, como cargar datos o configurar servicios
    }
}
/*
    *  Alternativa: Implementar InitializingBean
    *  Otra opción es implementar la interfaz InitializingBean y sobrescribir el método
    *  afterPropertiesSet(). Esto se ejecutará después de que las propiedades del bean sean configuradas.
*/
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class MyBean implements InitializingBean {

    @Override
    public void afterPropertiesSet() {
        System.out.println("Inicialización con InitializingBean...");
    }
}
/*
    *  Casos de Uso Comunes
*/
@Component
public class DataLoader {
    
    private final MyService myService;
    
    public DataLoader(MyService myService) {
        this.myService = myService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadData() {
        myService.cargarDatosIniciales();
    }
}
/*
    * Conectar con una API externa al inicio
*/
@Component
public class ApiConnector {

    private final ExternalApiService apiService;

    public ApiConnector(ExternalApiService apiService) {
        this.apiService = apiService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void connectToApi() {
        apiService.iniciarConexion();
    }
}
/*
    * Conclusión
    * Spring Boot 3+ ya no incluye @PostConstruct de forma predeterminada.
    * La mejor alternativa es @EventListener(ApplicationReadyEvent.class).
    * También puedes usar InitializingBean si prefieres una solución basada en interfaces.
    * Sí, con @EventListener(ApplicationReadyEvent.class) puedes garantizar que tu lógica de inicialización
    * (como consumir un servicio externo) se ejecute una vez que la aplicación ya ha arrancado completamente.
    * Casos en los que es útil usar @EventListener(ApplicationReadyEvent.class)
    * Necesitas cargar datos desde un servicio externo antes de que la aplicación comience a procesar solicitudes.
    * Requieres inicializar una conexión con una API externa o configurar un cliente HTTP.
    * Quieres asegurarte de que ciertos procesos críticos se ejecuten después del arranque de la aplicación.
    * Ejemplo: Consumir un servicio externo al inicio
    * Si necesitas obtener datos desde una API REST o algún otro servicio externo antes de que la aplicación empiece a operar, puedes hacerlo así:
*/
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final ExternalService externalService;

    public DataInitializer(ExternalService externalService) {
        this.externalService = externalService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fetchDataOnStartup() {
        System.out.println("📡 Obteniendo datos desde el servicio externo...");

        String data = externalService.getInitialData(); // *  Llamada a la API externa
        System.out.println("✅ Datos obtenidos: " + data);
    }
}
/*
    * Suponiendo que tienes un servicio externo como este:
*/
import org.springframework.stereotype.Service;

@Service
public class ExternalService {

    public String getInitialData() {
        // ! Simulación de una llamada HTTP a una API externa
        return "Datos cargados correctamente desde la API externa";
    }
}

/*
    * Importante: ¿Esto garantiza que el dato estará disponible antes de que se procese cualquier petición?
    * Depende del flujo de tu aplicación.
    * Si otros beans dependen de esta información desde el inicio, podrías considerar:
    * Cargar los datos en un @Bean durante la configuración en una clase @Configuration.
    * Usar un CommandLineRunner o ApplicationRunner si necesitas la información antes de iniciar procesos específicos.
    * Ejemplo con CommandLineRunner (Útil si necesitas los datos antes de que otros procesos arranquen)
*/
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {

    private final ExternalService externalService;

    public StartupRunner(ExternalService externalService) {
        this.externalService = externalService;
    }

    @Override
    public void run(String... args) {
        System.out.println(" Ejecutando tarea al inicio...");
        String data = externalService.getInitialData();
        System.out.println(" Datos cargados en StartupRunner: " + data);
    }
}
/*
    * ¿Cuál usar?
    * Método	                                    Se ejecuta	                                             Ideal para
    
    * @EventListener(ApplicationReadyEvent.class)	Después de que la aplicación arranque completamente      Inicializar datos o conectar con servicios
    *                                                                                                        externos sin bloquear el arranque.
                                               	
    * CommandLineRunner / ApplicationRunner         Justo después de que Spring Boot haya inicializado el    Procesos críticos que deben
    *                                               contexto                                                 ejecutarse antes de aceptar solicitudes.
    
    * Conclusión
    * Sí, @EventListener(ApplicationReadyEvent.class) es ideal si necesitas cargar datos de un servicio externo después del arranque.
    * Si la aplicación depende de esos datos antes de funcionar, mejor usa CommandLineRunner.
    * Ambas opciones garantizan que el código se ejecuta solo una vez, cuando la aplicación arranca.

*/
