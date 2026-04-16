package Ejercicio_Vehiculo;

public class AutoFactory extends VehiculoFactory{
    @Override
    public Vehiculo crearVehiculo() {
        return new Auto();
    }
}
