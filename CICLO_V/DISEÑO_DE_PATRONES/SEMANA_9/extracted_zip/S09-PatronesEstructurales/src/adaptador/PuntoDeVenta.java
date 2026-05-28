/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adaptador;

/**
 *
 * @author c19255
 */
public class PuntoDeVenta {
    private ProcesadorDePagos procesador;

    public PuntoDeVenta(ProcesadorDePagos procesador) {
        this.procesador = procesador;
    }
    
    public void cobrar(String tarjeta, double monto){
        procesador.pagar(tarjeta, monto); 
    }
    
}
