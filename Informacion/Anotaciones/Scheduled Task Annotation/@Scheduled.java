/*
    * En Spring, la anotación @Scheduled se conoce como una anotación de programación de tareas programadas
    * (Scheduled Task Annotation).Se utiliza para ejecutar métodos de manera periódica o en momentos específicos, 
    * sin necesidad de usar hilos manualmente. Es parte del módulo Spring Task Scheduling y se habilita con la anotación
    *  @EnableScheduling en la clase de configuración.
    * 
*/
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TareaProgramada {

    // TODO Ejecuta cada 5 segundos (5000 milisegundos)
    @Scheduled(fixedRate = 5000)
    public void ejecutarCada5Segundos() {
        System.out.println("Tarea ejecutada: " + System.currentTimeMillis());
    }

    // TODO Ejecuta cada 5 segundos después de que la tarea anterior haya finalizado
    @Scheduled(fixedDelay = 5000)
    public void ejecutarConRetraso() {
        System.out.println("Tarea con retraso ejecutada: " + System.currentTimeMillis());
    }

     // TODO Ejecuta la tarea a una hora específica usando cron
    @Scheduled(cron = "0 0 12 * * ?") // ! Se ejecuta todos los días a las 12:00 PM
    public void ejecutarConCron() {
        System.out.println("Tarea programada con cron ejecutada a las 12:00 PM");
    }
}
