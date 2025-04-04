/*
    * Observer
    * Patrón: Observer
    * Descripción: Permite que un objeto (sujeto) notifique a otros objetos (observadores) sobre cambios en su estado.
    * Ejemplo en Spring Boot: Uso de ApplicationEventPublisher
*/
@Component
public class PublicadorEventos {
    @Autowired
    private ApplicationEventPublisher publisher;

    public void publicarEvento(String mensaje) {
        publisher.publishEvent(new MiEvento(this, mensaje));
    }
}
/*
    * Uso en Spring: ApplicationEventPublisher, @EventListener.
*/