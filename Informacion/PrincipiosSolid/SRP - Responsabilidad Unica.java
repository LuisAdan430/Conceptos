/*
    * Principio de Responsabilidad Única (Single Responsibility Principle - SRP)
    * Una clase debe tener solo una razón para cambiar, es decir, solo una responsabilidad.
    * Ejemplo en Java:
*/
// ! INCORRECTO: La clase maneja tanto la lógica de negocio como la impresión.
class Reporte {
    public void generarReporte() {
        // ^  Lógica para generar el reporte
    }
    public void imprimirReporte() {
        //  ^ Lógica para imprimir el reporte
    }
}

// *  CORRECTO: Separar responsabilidades
class Reporte {
    public void generarReporte() {
        // ^ Lógica para generar el reporte
    }
}

class Impresora {
    public void imprimirReporte(Reporte reporte) {
        //  ^ Lógica para imprimir el reporte
    }
}
/*
    * El principio de Responsabilidad Única (SRP - Single Responsibility Principle) es el primero de los cinco
    * principios SOLID en el diseño de software orientado a objetos. Este principio establece que:
    & "Una clase debe tener una, y solo una, razón para cambiar."
    * Explicación
    * Cada clase, módulo o función en un sistema debe tener una única responsabilidad clara y bien definida.
    * Esto significa que una clase solo debe encargarse de una funcionalidad específica dentro de la
    * aplicación. Si una clase tiene múltiples responsabilidades, se vuelve difícil de mantener, probar y
    * extender.
    * Beneficios
    *   Código más limpio y mantenible: Facilita la comprensión del código.
    *   Menos acoplamiento: Reduce las dependencias entre clases.
    *   Facilidad de prueba: Se pueden probar los componentes de forma aislada.
    *   Mayor escalabilidad: Es más fácil modificar o extender el código sin afectar otras partes del sistema.
    *  Ejemplo en Java (Mal diseño vs. Buen diseño)
    * Esta clase tiene dos responsabilidades: manejar datos del empleado y generar reportes.
*/ 
public class Empleado {
    private String nombre;
    private double salario;

    public Empleado(String nombre, double salario) {
        this.nombre = nombre;
        this.salario = salario;
    }

    public void calcularSalario() {
        // ^ Lógica para calcular el salario
    }

    public void generarReporte() {
        // ^ Lógica para generar un reporte del empleado (violación del SRP)
    }
}
/*
    *  Problema: Si queremos cambiar la forma en que se generan los reportes, tendríamos que modificar esta clase, 
    *  afectando potencialmente la lógica de negocio del empleado.
    *   Aplicando SRP
    *  Separamos las responsabilidades en dos clases:
*/
public class Empleado {
    private String nombre;
    private double salario;

    public Empleado(String nombre, double salario) {
        this.nombre = nombre;
        this.salario = salario;
    }

    public void calcularSalario() {
        //  ^ Lógica para calcular el salario
    }
}

//  ^ Nueva clase para generar reportes (responsabilidad separada)
public class GeneradorReporte {
    public void generarReporte(Empleado empleado) {
        //  ^ Lógica para generar el reporte
    }
}

/*
    * Ventajas:
    * Cada clase tiene una única responsabilidad.
    * Los cambios en la generación de reportes no afectan la lógica del empleado.
    * Es más fácil probar y mantener el código.
    
    * Casos Comunes de Violación del SRP
    * Clases "Dios" o monolíticas: Clases que manejan múltiples funcionalidades, como procesamiento de datos, 
    * lógica de negocio y acceso a la base de datos.
    * Métodos que hacen demasiadas cosas: Un método que, además de calcular algo, guarda en base de datos y envía un correo.
    * Módulos que mezclan lógica de negocio y presentación: Código que genera una vista HTML y, al mismo tiempo, manipula datos de negocio.
    
    ^  Resumen
    ^       Cada clase debe tener una sola razón para cambiar.
    ^       Separa responsabilidades en clases diferentes.
    ^       Facilita la mantenibilidad, pruebas y extensibilidad del código. 
*/