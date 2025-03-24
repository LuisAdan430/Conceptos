/*
    TODO  :: ¿Qué hace la anotación @Autowired en Spring?
    * La anotación @Autowired en Spring se usa para inyectar dependencias automáticamente en los
    * componentes de la aplicación. Permite que Spring resuelva y asigne automáticamente una instancia de
    * un bean a una propiedad, un constructor o un método.
    TODO  :: ¿A qué tipo de anotaciones pertenece?
    * @Autowired pertenece al grupo de anotaciones de inyección de dependencias (Dependency Injection
    * - DI) dentro del framework de Spring. Es parte del paquete
    * org.springframework.beans.factory.annotation.
    TODO  :: Posibles usos de @Autowired
    ! Inyección en un campo
*/
@Component
public class MiServicio {
    @Autowired
    private MiRepositorio miRepositorio;
}
/*
    ! Spring inyecta automáticamente miRepositorio si está definido como un bean.
    ! Inyección en un constructor (Recomendado por buenas prácticas)
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
    * Favorece la inmutabilidad y facilita las pruebas unitarias.
    * Desde Spring 4.3, si solo hay un constructor, @Autowired es opcional.
    * Inyección en un método setter
*/
@Component
public class MiServicio {
    private MiRepositorio miRepositorio;

    @Autowired
    public void setMiRepositorio(MiRepositorio miRepositorio) {
        this.miRepositorio = miRepositorio;
    }
}
/*
    *  Útil cuando la dependencia es opcional o puede cambiar dinámicamente.
    *  Inyección en un método específico
*/
@Component
public class MiComponente {
    private MiServicio miServicio;

    @Autowired
    public void configurarServicio(MiServicio miServicio) {
        this.miServicio = miServicio;
    }
}
/*
    * Útil para inicializar dependencias después de la creación del bean.
    * Consideraciones y Alternativas
    * Si hay múltiples implementaciones del mismo tipo, usa @Qualifier para especificar cuál inyectar.
*/
@Autowired
@Qualifier("miImplementacionEspecifica")
private MiRepositorio miRepositorio;
/*
    ^ Para evitar problemas de dependencias nulas, puedes usar @Autowired(required = false).
    ^ Alternativas modernas incluyen @Inject (de Jakarta) y @Resource (de Java EE).
    ^ Conclusión: @Autowired simplifica la inyección de dependencias y permite que Spring gestione
    ^ automáticamente los beans, facilitando el desarrollo de aplicaciones modulares y desacopladas.
*/