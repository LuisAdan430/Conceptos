/*
    ^ Inyección por Constructor (Constructor Injection)
    ^ Se inyectan las dependencias a través del constructor de la clase. Es el método más recomendado, ya
    ^ que garantiza que la dependencia esté disponible en el momento de la creación del objeto y facilita la
    ^ inmutabilidad.
    ^ Ejemplo en Java con Spring Boot:
*/

@Component
public class Servicio {
    public void ejecutar() {
        System.out.println("Servicio ejecutado");
    }
}

@Service
public class Cliente {
    private final Servicio servicio;

    @Autowired
    public Cliente(Servicio servicio) {  // * Inyección a través del constructor
        this.servicio = servicio;
    }

    public void realizarAccion() {
        servicio.ejecutar();
    }
}
