package Ejercicio_cafeteria;

public class TeFactory extends BebidaFactory{
    @Override
    public Bebida crearBebida() {
        return new Te();
    }
}
