/*
*@Component
*Es la anotación base de todas las anotaciones estereotipadas.
*Indica que una clase es un componente administrado por Spring.
*Se usa para componentes genéricos que no encajan en otros estereotipos
*/

@Component
public class MiServicio {
    public String obtenerMensaje() {
        return "Hola desde MiServicio";
    }
}

/*
 & Uso recurrente de @Component en Spring
 & La anotación @Component se usa para definir beans genéricos en el contenedor de Spring. Se usa
 & principalmente cuando una clase no encaja directamente en otras especializaciones como @Service,
 & @Repository o @Controller.
 */

/*
 ^  Casos de Uso Comunes de @Component
 ^  1. Clases Utilitarias o de Ayuda
 ^  Se usa en clases de utilidad o servicios que no manejan lógica de negocio directamente.
*/
@Component
public class FechaUtil {
    public String obtenerFechaActual() {
        return LocalDate.now().toString();
    }
}

/*
  ^  Spring lo registrará como un bean y podrá ser inyectado en otras clases.
*/
/*
  ! Inyección de Dependencias Personalizadas
  ! Cuando creas clases personalizadas que deben ser administradas por Spring.
*/
@Component
public class ClienteHelper {
    public String formatearNombre(String nombre) {
        return "Cliente: " + nombre;
    }
}
/*
  ! Luego puedes inyectarlo en otros componentes:
*/
@Service
public class ClienteService {
    private final ClienteHelper clienteHelper;

    @Autowired
    public ClienteService(ClienteHelper clienteHelper) {
        this.clienteHelper = clienteHelper;
    }

    public String procesarCliente(String nombre) {
        return clienteHelper.formatearNombre(nombre);
    }
}
/*
    ? Beans de Configuración con @Component y @Value
    ? Se usa cuando necesitas cargar valores de configuración desde application.properties.
 */
@Component
public class ConfiguracionApp {
    @Value("${app.nombre}")
    private String nombreApp;

    public String obtenerNombreApp() {
        return nombreApp;
    }
}
/* 
    ? ConfiguracionApp podrá ser inyectado en otros componentes.
*/
/*
    TODO : Eventos con @Component y ApplicationListener
    TODO : Se usa para manejar eventos dentro de Spring.
*/
@Component
public class MiEventoListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("🚀 La aplicación se ha iniciado correctamente.");
    }
}
/*
    TODO: Se ejecutará automáticamente cuando el contexto de Spring se refresque.
*/

/*
    * Programación con Tareas Automáticas @Scheduled
    * Se usa para ejecutar tareas en segundo plano automáticamente.
 */
@Component
public class TareaAutomatica {
    @Scheduled(fixedRate = 5000)
    public void ejecutarTarea() {
        System.out.println("Ejecutando tarea cada 5 segundos...");
    }
}
/*
    * Spring ejecutará este método cada 5 segundos.
 */
/*
    TODO :  ¿Cuándo usar @Component en lugar de otras anotaciones?
    TODO :  Anotación	¿Cuándo usarla?
    TODO :  @Component	Cuando no encaja en @Service, @Repository o @Controller.
    TODO :  @Service	Para lógica de negocio.
    TODO :  @Repository	Para clases de acceso a datos.
    TODO :  @Controller	Para controladores MVC.
    TODO :  @RestController	Para APIs REST.
    ! @Component es la base de todas las anotaciones estereotipadas 
    ! y se usa cuando se necesita un bean administrado por Spring sin un propósito específico.
 */