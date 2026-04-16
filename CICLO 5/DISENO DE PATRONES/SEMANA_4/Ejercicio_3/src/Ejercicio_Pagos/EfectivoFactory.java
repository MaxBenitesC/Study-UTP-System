package Ejercicio_Pagos;

public class EfectivoFactory extends PagosFactory{
    @Override
    public Pagos generarPago() {
        return new Efectivo();
    }
}
