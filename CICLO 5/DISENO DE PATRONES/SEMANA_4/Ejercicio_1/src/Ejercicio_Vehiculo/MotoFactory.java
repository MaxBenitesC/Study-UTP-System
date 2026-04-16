package Ejercicio_Vehiculo;

public class MotoFactory extends VehiculoFactory{
    @Override
    public Vehiculo crearVehiculo() {
        return new Moto();
    }
}
