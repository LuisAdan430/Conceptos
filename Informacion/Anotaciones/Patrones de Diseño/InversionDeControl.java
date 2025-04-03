/*
    ^ Inversión de Control (IoC) y Dependency Injection (DI)
    ^ Patrón: Dependency Injection
    ^ Descripción: Spring gestiona las dependencias de los objetos en lugar de que los objetos las creen manualmente.
    ^ Ejemplo en Spring Boot:
*/
@Service
public class MiServicio {
    private final MiRepositorio miRepositorio;

    @Autowired
    public MiServicio(MiRepositorio miRepositorio) {
        this.miRepositorio = miRepositorio;
    }
}
/*
    ^ Uso en Spring: @Component, @Service, @Repository, @Autowired, @Bean.
*/