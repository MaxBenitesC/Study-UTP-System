package Ejercicio_cafeteria;

public class CafeFactory extends BebidaFactory{
    @Override
    public Bebida crearBebida() {
        return new Cafe();
    }
}
