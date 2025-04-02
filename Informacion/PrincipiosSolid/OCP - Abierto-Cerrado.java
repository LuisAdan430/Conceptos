/*
   ^ Principio de Abierto/Cerrado (Open/Closed Principle - OCP)
   ^ El código debe estar abierto para extensión, pero cerrado para modificación.
   ^ Ejemplo en Java:
*/
// * INCORRECTO: Cada vez que agregamos un nuevo tipo de pago, debemos modificar la clase.
class ProcesadorPagos {
    public void procesarPago(String tipo) {
        if (tipo.equals("tarjeta")) {
            // * Procesa pago con tarjeta
        } else if (tipo.equals("paypal")) {
            // *  Procesa pago con PayPal
        }
    }
}

// * CORRECTO: Uso de polimorfismo para evitar modificar la clase.
interface MetodoPago {
    void procesarPago();
}

class PagoTarjeta implements MetodoPago {
    public void procesarPago() {
        System.out.println("Pago con tarjeta procesado");
    }
}

class PagoPayPal implements MetodoPago {
    public void procesarPago() {
        System.out.println("Pago con PayPal procesado");
    }
}

class ProcesadorPagos {
    public void procesar(MetodoPago metodoPago) {
        metodoPago.procesarPago();
    }
}
/*
    ^ El Principio de Abierto/Cerrado (Open/Closed Principle - OCP) es uno de los principios SOLID de la
    ^ programación orientada a objetos. Fue formulado por Bertrand Meyer y establece que:
    ^ "Las entidades de software (clases, módulos, funciones, etc.) deben estar abiertas para su extensión, pero cerradas para su modificación."
    ^ ¿Qué significa esto en la práctica?
    ^ El OCP sugiere que una clase o módulo de software debería poder ser extendido sin necesidad de
    ^ modificar su código fuente original. Esto es clave para reducir el riesgo de introducir errores al cambiar
    ^ código ya existente y mejorar la mantenibilidad del software.
    ^ ¿Cómo aplicar el Principio de Abierto/Cerrado?
    ^ Para cumplir con este principio, se pueden emplear técnicas como:
    ^ Herencia y Polimorfismo: Crear una clase base y extender su funcionalidad mediante subclases en lugar de modificar la clase original.
    ^ Interfaces y Abstracción: Usar interfaces o clases abstractas para definir comportamientos genéricos que luego pueden ser implementados o sobrescritos.
    ^ Patrones de Diseño como el Patrón Estrategia o el Patrón Decorador: Permiten añadir funcionalidades sin modificar la estructura original del código.
    ^ Ejemplo sin OCP (Malo)
*/
class CalculadoraDescuento {
    public double calcularDescuento(String tipoCliente, double precio) {
        if (tipoCliente.equals("ESTUDIANTE")) {
            return precio * 0.20; // *  20% de descuento
        } else if (tipoCliente.equals("JUBILADO")) {
            return precio * 0.30; // * 30% de descuento
        } else {
            return 0; // * Sin descuento
        }
    }
}
/*
    *  Problema: Cada vez que se necesite agregar un nuevo tipo de cliente con descuento, habrá que modificar esta clase, violando el principio de OCP.
    *  Ejemplo aplicando OCP (Bueno)
*/
// * Interfaz para descuentos
interface Descuento {
    double aplicarDescuento(double precio);
}

// * Implementaciones específicas
class DescuentoEstudiante implements Descuento {
    public double aplicarDescuento(double precio) {
        return precio * 0.20;
    }
}

class DescuentoJubilado implements Descuento {
    public double aplicarDescuento(double precio) {
        return precio * 0.30;
    }
}

// * Clase que usa la abstracción sin modificar el código original
class CalculadoraDescuento {
    public double calcular(Descuento descuento, double precio) {
        return descuento.aplicarDescuento(precio);
    }
}

// * Uso en el código
public class Main {
    public static void main(String[] args) {
        CalculadoraDescuento calculadora = new CalculadoraDescuento();
        
        double precio = 100;
        Descuento descuento = new DescuentoEstudiante();  // *  Se puede cambiar sin modificar CalculadoraDescuento
        
        System.out.println("Descuento aplicado: " + calculadora.calcular(descuento, precio));
    }
}
/*
    * Ventajas de este enfoque:
    * No es necesario modificar CalculadoraDescuento para añadir nuevos tipos de descuentos.
    * Se pueden agregar nuevas implementaciones de Descuento sin afectar el código existente.
    * Hace que el código sea más fácil de probar y mantener.

    * Resumen
    * El OCP evita que modifiquemos código existente cuando queremos agregar nuevas funcionalidades.
    * Se logra mediante abstracción, interfaces y polimorfismo.
    * Mejora la escalabilidad, reduce el riesgo de errores y facilita la extensión del código.

*/ 