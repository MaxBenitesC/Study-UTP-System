package Ejercicio_Vehiculo;

public class CamionFactory extends VehiculoFactory{
    @Override
    public Vehiculo crearVehiculo() {
        return new Camion();
    }
}
