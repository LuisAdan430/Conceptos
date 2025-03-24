/*
  ^  ¿Qué es una Clase Inmutable en Java?
  ^  Una clase inmutable en Java es aquella cuyos objetos no pueden modificarse después de haber sido creados. Es decir, 
  ^  una vez que se asignan valores a sus atributos, estos no pueden cambiar. 
  &  Un ejemplo clásico de una clase inmutable en Java es String:
*/


String texto = "Hola";
texto = texto.concat(" Mundo"); // * Se crea un nuevo objeto en lugar de modificar el existente

/*
    * Aquí, "Hola" sigue existiendo en la memoria, pero texto ahora apunta a un nuevo objeto "Hola Mundo". 
    * Características de una Clase Inmutable
    * Para hacer una clase inmutable, sigue estas reglas:
    * Declarar la clase como final
    * Evita que otras clases la extiendan y modifiquen su comportamiento.
*/
public final class Persona { }
/*
    * Hacer que todos los atributos sean private y final
    * Así, solo se pueden asignar valores una vez en el constructor.
*/
private final String nombre;
private final int edad;
/*
    * No proporcionar métodos setter
    * No se deben permitir cambios después de la creación.
    * Inicializar los atributos en el constructor 
*/

public Persona(String nombre, int edad) {
    this.nombre = nombre;
    this.edad = edad;
}
/*
    * Evitar devolver referencias a objetos mutables
    * Si tienes atributos que son objetos mutables, devuelve copias en lugar del objeto original.
    TODO : Ejemplo de una Clase Inmutable
*/

public final class Persona {
    private final String nombre;
    private final int edad;

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }
}
/*
    *  Ventajas de las Clases Inmutables
    *  Seguridad en aplicaciones concurrentes (no hay modificaciones inesperadas).
    *  Fácil de razonar y depurar (el estado no cambia).
    *  Uso eficiente en estructuras de datos como HashMap o HashSet (no se necesita recalcular hashCode).
    *  Si necesitas modificar valores, la opción es crear un nuevo objeto en lugar de modificar el existente:
*/
Persona p1 = new Persona("Juan", 30);
Persona p2 = new Persona("Juan", 31); // * Nuevo objeto en lugar de modificar el anterior


