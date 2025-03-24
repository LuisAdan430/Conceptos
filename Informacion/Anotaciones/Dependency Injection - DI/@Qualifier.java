/*
 ^ La anotación @Qualifier en Spring Framework se 
 ^ utiliza para resolver ambigüedades cuando hay múltiples beans del mismo tipo y 
 ^ el contenedor de Spring no sabe cuál inyectar. Se usa junto con @Autowired o @Inject
 ^ para especificar cuál bean debe ser inyectado en un punto específico.
 * ¿Por qué es necesaria @Qualifier?
 ^ Cuando existen varios beans del mismo tipo en el contexto de Spring, el framework no puede
 ^ determinar automáticamente cuál debe inyectar y lanza una excepción. @Qualifier permite seleccionar
 ^ el bean adecuado por su nombre específico. 
 ! Ejemplo sin @Qualifier (Provoca error)
*/
@Component
class MotorDiesel implements Motor {
    @Override
    public String tipo() {
        return "Motor Diesel";
    }
}

@Component
class MotorGasolina implements Motor {
    @Override
    public String tipo() {
        return "Motor Gasolina";
    }
}

@Component
class Coche {

    @Autowired
    private Motor motor; // ! ❌ ERROR: Hay 2 beans de tipo Motor (MotorDiesel y MotorGasolina)

    public void mostrarMotor() {
        System.out.println(motor.tipo());
    }
}

/*
    * Error: No qualifying bean of type 'Motor' available
    * Solución con @Qualifier
*/
@Component
class Coche {

    @Autowired
    @Qualifier("motorDiesel") //  ^ Se inyecta el bean con el nombre "motorDiesel"
    private Motor motor;

    public void mostrarMotor() {
        System.out.println(motor.tipo());
    }
}
/*
    ^  Spring elegirá el bean cuyo nombre coincida con "motorDiesel".
    ^  ¿Cómo sabe Spring el nombre del Bean?
    ^  Por defecto, Spring asigna el nombre del bean en minúscula, basado en el nombre de la clase:
    &  MotorDiesel → "motorDiesel"
    &  MotorGasolina → "motorGasolina"
    ^  Si quieres definir un nombre personalizado para el bean, puedes usar
    ^  @Component("nombrePersonalizado"):
*/
@Component("diesel")
class MotorDiesel implements Motor { }

/*
    * Y luego en la inyección:
*/
@Autowired
@Qualifier("diesel")
private Motor motor;

/*
    TODO: Uso con @Bean en @Configuration
    TODO: También puedes usar @Qualifier cuando defines beans en una clase @Configuration:
*/
@Configuration
public class AppConfig {

    @Bean
    public Motor motorDiesel() {
        return new MotorDiesel();
    }

    @Bean
    public Motor motorGasolina() {
        return new MotorGasolina();
    }
}

/*
    TODO: Luego, en la inyección:
*/
@Autowired
@Qualifier("motorGasolina")
private Motor motor;

/*
    ~ Uso con @Qualifier en métodos
    ~ También puedes usar @Qualifier en el constructor o métodos setter:
*/
@Component
class Coche {

    private final Motor motor;

    @Autowired
    public Coche(@Qualifier("motorGasolina") Motor motor) {
        this.motor = motor;
    }

    public void mostrarMotor() {
        System.out.println(motor.tipo());
    }
}
/*
    !  ¿Cuándo usar @Primary en lugar de @Qualifier?
    !  Si tienes un bean que quieres que sea el predeterminado, usa @Primary en lugar de @Qualifier.
*/
@Component
@Primary  // ! Este será el bean principal si no se usa @Qualifier
class MotorGasolina implements Motor { }
/*
    ! Ahora, si inyectas Motor sin @Qualifier, Spring usará el MotorGasolina por defecto.
    ! Resumen
    ! @Qualifier se usa para especificar qué bean inyectar cuando hay varios candidatos.
    ! Se usa con @Autowired o @Inject.
    ! Se puede aplicar en campos, constructores y métodos setter.
    ! Alternativa: @Primary define un bean predeterminado.
*/

