// Declaración de una clase abstracta
abstract class Animal {
    
    // Método abstracto (sin implementación)
    abstract void hacerSonido();
    
    // Método concreto (con implementación)
    void dormir() {
        System.out.println("El animal está durmiendo.");
    }
}

// Subclase que extiende de la clase abstracta
class Perro extends Animal {
    
    // Implementación del método abstracto
    @Override
    void hacerSonido() {
        System.out.println("El perro dice: Guau Guau");
    }
}

// Clase principal para probar
public class Main {
    public static void main(String[] args) {
        // No se puede instanciar la clase abstracta
        // Animal animal = new Animal(); // Esto daría error de compilación

        // Se puede instanciar una subclase concreta
        Perro perro = new Perro();
        perro.hacerSonido(); // Salida: El perro dice: Guau Guau
        perro.dormir();      // Salida: El animal está durmiendo.
    }
}
