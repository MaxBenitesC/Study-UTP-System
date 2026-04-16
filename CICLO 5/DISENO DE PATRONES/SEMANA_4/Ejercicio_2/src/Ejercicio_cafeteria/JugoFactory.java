package Ejercicio_cafeteria;

public class JugoFactory extends BebidaFactory {
    @Override
    public Bebida crearBebida() {
        return new Jugo();
    }
}
