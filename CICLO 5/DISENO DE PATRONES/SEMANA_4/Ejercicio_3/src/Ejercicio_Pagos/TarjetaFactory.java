package Ejercicio_Pagos;

public class TarjetaFactory extends PagosFactory{
    @Override
    public Pagos generarPago() {
        return new Tarjeta();
    }
}
