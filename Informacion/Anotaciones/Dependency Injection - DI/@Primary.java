/*
    ? @Primary en Spring
    ? La anotación @Primary en Spring se usa para marcar un bean como la implementación predeterminada
    ? cuando hay múltiples candidatos del mismo tipo.
    ? Ejemplo de uso de @Primary
    ? Supongamos que tienes dos implementaciones de una interfaz Notificador:
*/
public interface Notificador {
    void enviarMensaje(String mensaje);
}
/*
    ? Dos implementaciones del mismo tipo
*/
import org.springframework.stereotype.Component;

@Component
public class EmailNotificador implements Notificador {
    @Override
    public void enviarMensaje(String mensaje) {
        System.out.println("Enviando email: " + mensaje);
    }
}
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Primary;

@Component
@Primary  //  ? Esta será la implementación principal si no se usa @Qualifier
public class SmsNotificador implements Notificador {
    @Override
    public void enviarMensaje(String mensaje) {
        System.out.println("Enviando SMS: " + mensaje);
    }
}
/*
    ? Inyección en otra clase
*/
import org.springframework.stereotype.Service;

@Service
public class ServicioDeNotificacion {

    private final Notificador notificador;

    public ServicioDeNotificacion(Notificador notificador) {
        this.notificador = notificador;
    }

    public void enviar(String mensaje) {
        notificador.enviarMensaje(mensaje);
    }
}
/*
    ? Comportamiento
    ? Como SmsNotificador tiene @Primary, Spring lo usará por defecto en ServicioDeNotificacion.
    ? Si queremos inyectar EmailNotificador, debemos usar @Qualifier("emailNotificador") en el constructor.
    ? Casos donde @Primary es útil
    ? Cuando tienes múltiples implementaciones de una interfaz y quieres definir una predeterminada sin usar @Qualifier
    ? Cuando trabajas con configuraciones en Spring Boot, como DataSource, donde puedes marcar una fuente de datos primaria.
*/