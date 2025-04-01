/*
    * La etiqueta @SpringBootApplication es una anotación de Spring Boot que se utiliza para marcar la clase principal 
    * de una aplicación Spring Boot. Es una anotación compuesta que agrupa tres anotaciones clave:
    * Anotaciones incluidas en @SpringBootApplication
    ! @SpringBootConfiguration
    ^   Es una extensión de @Configuration, lo que indica que la clase puede contener definiciones de beans para el contexto de la aplicación.
    ! @EnableAutoConfiguration 
    ^   Habilita la configuración automática de Spring Boot, detectando y configurando automáticamente beans según las dependencias del proyecto.
    ! @ComponentScan
    ^   Activa el escaneo de componentes (@Component, @Service, @Repository, @Controller, etc.) en el paquete donde
    ^   se encuentra la clase anotada y sus subpaquetes.
    TODO: Ejemplo de Uso
*/
@SpringBootApplication
public class MiAplicacion {
    public static void main(String[] args) {
        SpringApplication.run(MiAplicacion.class, args);
    }
}
/*
    * ¿A qué otras etiquetas pertenece?
    * Dado que @SpringBootApplication es una anotación compuesta, pertenece indirectamente a:
    * @SpringBootConfiguration
    * @EnableAutoConfiguration
    * @ComponentScan
    * @Configuration (porque @SpringBootConfiguration extiende @Configuration)
    * Esta anotación simplifica la configuración de una aplicación Spring Boot, evitando la necesidad de definir
    *  manualmente cada una de estas anotaciones por separado.
*/