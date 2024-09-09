// Declaración de una interfaz
interface Volador {
    // Método abstracto
    void volar();
    
    // Método default con implementación (Java 8 en adelante)
    default void despegar() {
        System.out.println("Preparando para el despegue...");
    }

    // Método static con implementación (Java 8 en adelante)
    static void mantenimiento() {
        System.out.println("Realizando mantenimiento del sistema de vuelo.");
    }
}

// Clase que implementa la interfaz
class Avion implements Volador {
    
    // Implementación del método abstracto
    @Override
    public void volar() {
        System.out.println("El avión está volando.");
    }
}

// Clase principal para probar
public class Main {
    public static void main(String[] args) {
        Avion avion = new Avion();
        avion.despegar();  // Salida: Preparando para el despegue...
        avion.volar();     // Salida: El avión está volando.
        
        // Llamada al método estático de la interfaz
        Volador.mantenimiento();  // Salida: Realizando mantenimiento del sistema de vuelo.
    }
}
