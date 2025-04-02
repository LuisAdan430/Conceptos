/*
    TODO: Principio de Sustitución de Liskov (Liskov Substitution Principle - LSP)
    TODO: Las subclases deben poder sustituir a sus clases base sin alterar el funcionamiento del programa.
    TODO: Ejemplo en Java
*/
//  TODO: INCORRECTO: El rectángulo y el cuadrado no son completamente intercambiables.
class Rectangulo {
    protected int ancho, alto;
    
    public void setAncho(int ancho) { this.ancho = ancho; }
    public void setAlto(int alto) { this.alto = alto; }
    public int getArea() { return ancho * alto; }
}

class Cuadrado extends Rectangulo {
    @Override
    public void setAncho(int ancho) {
        super.setAncho(ancho);
        super.setAlto(ancho);  // TODO: Rompe el principio
    }
}

//  TODO: CORRECTO: Separar la jerarquía de clases.
interface Figura {
    int getArea();
}

class Rectangulo implements Figura {
    protected int ancho, alto;
    
    public Rectangulo(int ancho, int alto) {
        this.ancho = ancho;
        this.alto = alto;
    }
    
    public int getArea() { return ancho * alto; }
}

class Cuadrado implements Figura {
    private int lado;
    
    public Cuadrado(int lado) { this.lado = lado; }
    
    public int getArea() { return lado * lado; }
}
/*
    * El Principio de Sustitución de Liskov (LSP) es uno de los principios SOLID de la programación orientada a objetos. 
    * Fue formulado por Barbara Liskov en 1987 y establece que:
    * "Los objetos de una clase derivada deben poder ser sustituidos por objetos de su clase base sin alterar el comportamiento correcto del programa."
    * ¿Qué significa esto en la práctica?
    * Si tienes una clase base (superclase) y varias clases derivadas (subclases), las subclases deben poder reemplazar a la superclase sin que el programa 
    * falle o se comporte de manera inesperada.
    * Consecuencias de no cumplir el LSP
    * Si una subclase modifica el comportamiento esperado de la clase base, se pueden generar errores difíciles de detectar.
    * Ejemplo Correcto (Cumple con LSP)
    * Supongamos que tenemos una clase Ave y dos subclases Gorrion y Aguila:
*/
// * Superclase
class Ave {
    void volar() {
        System.out.println("Esta ave está volando.");
    }
}

// * Subclases
class Gorrion extends Ave {
    // * No cambia el comportamiento
}

class Aguila extends Ave {
    // *  No cambia el comportamiento
}
/*
    * Aquí, podemos sustituir Ave por Gorrion o Aguila sin que el código se rompa.
    * Ejemplo Incorrecto (No cumple con LSP)
    * Ahora agregamos una nueva subclase Pinguino, pero los pingüinos no pueden volar.
*/
class Pinguino extends Ave {
    @Override
    void volar() {
        throw new UnsupportedOperationException("Los pingüinos no pueden volar.");
    }
}
/*
    * Si usamos Pinguino en un código que espera un Ave, obtendremos un error inesperado.
*/
Ave miAve = new Pinguino();
miAve.volar(); //! ❌ Lanza una excepción
/*
    *  Problema: Pinguino rompe la expectativa de que todas las Aves pueden volar.
    *  Solución Correcta
    *  Podemos rediseñar la jerarquía de clases para que solo las aves que vuelan tengan el método volar():
*/
class Ave {
    void hacerSonido() {
        System.out.println("El ave hace un sonido.");
    }
}

class AveVoladora extends Ave {
    void volar() {
        System.out.println("Esta ave está volando.");
    }
}

class Gorrion extends AveVoladora {}
class Aguila extends AveVoladora {}
class Pinguino extends Ave {} //* No tiene el método volar()


/*
    * Beneficio: Ahora Pinguino no hereda volar(), evitando romper el LSP.
    * El Principio de Sustitución de Liskov (LSP) ayuda a diseñar jerarquías de clases más flexibles y robustas.
    * Si una subclase cambia el comportamiento esperado de la clase base, es una señal de que algo está mal
    * en el diseño y deberíamos reconsiderar la estructura.
*/