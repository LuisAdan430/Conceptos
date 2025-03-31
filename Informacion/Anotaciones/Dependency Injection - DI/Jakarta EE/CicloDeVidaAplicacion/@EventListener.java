/*
    * @EventListener se puede usar con diferentes eventos del ciclo de vida de Spring, no solo con
    * ApplicationReadyEvent. Dependiendo del momento en el que necesites ejecutar la lógica, puedes usar otros eventos.
    & Eventos más comunes con @EventListener
    * Cada uno se ejecuta en un momento diferente del ciclo de vida de la aplicación:

    ^ Evento	                                    Se ejecuta cuando...	                                     Uso común
    ^ ApplicationStartingEvent                      Spring Boot apenas comienza a iniciarse.                     Configurar logs o propiedades iniciales.

    ^ ApplicationEnvironmentPreparedEvent           El contexto de configuración está listo, pero aún no hay     Modificar propiedades antes de que se inicien los beans.
    ^                                               beans cargados.

    ^ ApplicationPreparedEvent                      El contexto de Spring está creado, pero los beans aún no     Configurar beans antes de su inicialización.
    ^                                               han sido inicializados.

    ^ ContextRefreshedEvent                         El contexto de Spring ha sido refrescado o reiniciado.       Usar cuando necesitas resetear datos al refrescar el contexto.
    ^
    ^ ApplicationStartedEvent                       La aplicación ya arrancó, pero  aún no está lista para       Procesos que deben ejecutarse después del arranque, pero antes
    ^                                               recibir peticiones.                                          de aceptar solicitudes.

    ^ ApplicationReadyEvent (Más común)             La aplicación ya está totalmente lista para aceptar          Cargar datos de un servicio externo, inicializar conexiones.
    ^                                               peticiones.
    ^
    ^ ApplicationFailedEvent                        Se generó un error y la aplicación no pudo iniciarse.        Registrar logs en caso de fallo.


    &  Ejemplo con ApplicationStartedEvent
    *  Si quieres ejecutar una tarea justo después de que la aplicación arranque, pero antes de recibir solicitudes, usa ApplicationStartedEvent:
*/
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StartupTask {

    @EventListener(ApplicationStartedEvent.class)
    public void onApplicationStarted() {
        System.out.println("🔥 La aplicación ha iniciado, pero aún no acepta solicitudes.");
    }
}
/*
    * Ejemplo con ContextRefreshedEvent
    * Si necesitas que un proceso se ejecute cada vez que el contexto de Spring se refresque (por ejemplo, si usas Spring Cloud para recargar configuración), usa ContextRefreshedEvent:
*/
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class RefreshListener {

    @EventListener(ContextRefreshedEvent.class)
    public void onContextRefreshed() {
        System.out.println("🔄 El contexto de Spring ha sido refrescado.");
    }
}
/*
    *  Ejemplo con ContextClosedEvent
    *  Si necesitas ejecutar código cuando la aplicación se apaga (por ejemplo, para cerrar conexiones o guardar logs), usa ContextClosedEvent:
*/
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ShutdownListener {

    @EventListener(ContextClosedEvent.class)
    public void onShutdown() {
        System.out.println("⏳ La aplicación se está cerrando. Guardando datos...");
    }
}
/*
    *  Conclusión
    *  @EventListener(ApplicationReadyEvent.class) es la opción más común, pero hay muchas variantes según el momento en el que necesites ejecutar la lógica.
    *  Si quieres que algo pase antes de recibir peticiones, usa ApplicationStartedEvent.
    *  Si quieres que algo pase cuando el contexto de Spring se refresca, usa ContextRefreshedEvent.
    *  Si necesitas guardar información al cerrar la aplicación, usa ContextClosedEvent.
    *  <Pruebas>
*/