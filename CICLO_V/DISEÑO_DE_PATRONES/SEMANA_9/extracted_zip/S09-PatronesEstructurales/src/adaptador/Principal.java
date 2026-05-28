/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adaptador;

/**
 *
 * @author c19255
 */
public class Principal {
    public static void main(String[] args) {
        
        PagoSeguroAPI proveedor= new PagoSeguroAPI();
        ProcesadorDePagos adaptador= new AdaptadorPagoSeguro(proveedor);
        
        PuntoDeVenta caja = new PuntoDeVenta(adaptador);
        caja.cobrar("1234-5678-9012-3123", 150.0);
        
        
        
        
    }
}
