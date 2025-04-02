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
