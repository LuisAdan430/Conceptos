/*
    *  Principio de Segregación de Interfaces (Interface Segregation Principle - ISP)
    *  No forzar a una clase a depender de interfaces que no usa.
    *  Ejemplo en Java:
*/
//  * INCORRECTO: La interfaz obliga a implementar métodos innecesarios.
interface Trabajador {
    void trabajar();
    void comer();
}

class Robot implements Trabajador {
    public void trabajar() { System.out.println("El robot está trabajando"); }
    public void comer() { throw new UnsupportedOperationException("Un robot no come"); } // No aplica
}

// * CORRECTO: Dividir en interfaces más específicas.
interface Trabajador {
    void trabajar();
}

interface Comedor {
    void comer();
}

class Humano implements Trabajador, Comedor {
    public void trabajar() { System.out.println("El humano está trabajando"); }
    public void comer() { System.out.println("El humano está comiendo"); }
}

class Robot implements Trabajador {
    public void trabajar() { System.out.println("El robot está trabajando"); }
}
