/*
    & Inyección por Método Setter (Setter Injection)
    & Las dependencias se inyectan mediante métodos setter en lugar de hacerlo en el constructor. Se usa
    & cuando la dependencia es opcional o puede cambiar después de la creación del objeto.
    & Ejemplo en Java con Spring Boot:
*/
@Service
public class Cliente {
    private Servicio servicio;

    @Autowired
    public void setServicio(Servicio servicio) {  // *Inyección a través del setter
        this.servicio = servicio;
    }

    public void realizarAccion() {
        servicio.ejecutar();
    }
}
/* Test */