/*
    TODO :  Inyección por Atributo o Campo (Field Injection)
    TODO :  Se inyecta la dependencia directamente en un atributo utilizando anotaciones. Es la forma más sencilla,
    TODO :  pero no es recomendable porque dificulta la prueba unitaria y reduce la encapsulación.
    TODO :  Ejemplo en Java con Spring Boot:
*/

@Service
public class Cliente {
    @Autowired
    private Servicio servicio;  // *  Inyección directa en el campo

    public void realizarAccion() {
        servicio.ejecutar();
    }
}
/**/