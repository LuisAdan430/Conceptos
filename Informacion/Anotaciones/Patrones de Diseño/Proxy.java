/*
    * Proxy
    * Patrón: Proxy
    * Descripción: Se usa para agregar funcionalidad adicional a un objeto sin modificar su estructura.
    * Ejemplo en Spring Boot: Transacciones con AOP
*/
@Service
public class MiServicio {
    @Transactional
    public void realizarOperacion() {
        // *  Código de la operación
    }
}
/*
    * Uso en Spring: @Transactional, @Cacheable, @Aspect.
*/
