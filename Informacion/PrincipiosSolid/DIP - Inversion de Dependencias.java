/*
    ^ Principio de Inversión de Dependencias (Dependency Inversion Principle - DIP)
    ^ Las clases deben depender de abstracciones y no de implementaciones concretas.
    ^ Ejemplo en Java:
*/
// ^ INCORRECTO: Depender directamente de una clase concreta.
class Motor {
    public void encender() { System.out.println("Motor encendido"); }
}

class Coche {
    private Motor motor;
    
    public Coche() {
        this.motor = new Motor(); // ^ Acoplamiento fuerte
    }
    
    public void arrancar() { motor.encender(); }
}

// ^ CORRECTO: Depender de una abstracción.
interface IMotor {
    void encender();
}

class MotorElectrico implements IMotor {
    public void encender() { System.out.println("Motor eléctrico encendido"); }
}

class MotorGasolina implements IMotor {
    public void encender() { System.out.println("Motor de gasolina encendido"); }
}

class Coche {
    private IMotor motor;

    public Coche(IMotor motor) { this.motor = motor; }

    public void arrancar() { motor.encender(); }
}

// ^ Uso:
Coche coche1 = new Coche(new MotorElectrico());
Coche coche2 = new Coche(new MotorGasolina());
