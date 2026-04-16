package Ejercicio_Vehiculo;

public class main {
    public static void main(String[] args) {
        //Crear auto
        VehiculoFactory factoryAuto = new AutoFactory();
        Vehiculo vehiculoAuto = factoryAuto.crearVehiculo();
        vehiculoAuto.arrancar();

        //Crear moto
        VehiculoFactory factoryMoto = new MotoFactory();
        Vehiculo vehiculoMoto = factoryMoto.crearVehiculo();
        vehiculoMoto.arrancar();

        //Crear camion
        VehiculoFactory factoryCamion = new CamionFactory();
        Vehiculo vehiculoCamion = factoryCamion.crearVehiculo();
        vehiculoCamion.arrancar();
    }
}
