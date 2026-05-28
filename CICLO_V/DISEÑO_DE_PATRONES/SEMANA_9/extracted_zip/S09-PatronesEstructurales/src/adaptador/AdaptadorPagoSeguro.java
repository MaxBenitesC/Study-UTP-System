/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adaptador;

/**
 *
 * @author c19255
 */
public class AdaptadorPagoSeguro implements ProcesadorDePagos{

    private PagoSeguroAPI api;
    
    public AdaptadorPagoSeguro(PagoSeguroAPI api){
     this.api=api;
    }
    
    @Override
    public void pagar(String numeroTarjeta, double monto) {
        api.realizarPago(monto, numeroTarjeta);
    }
    
}
