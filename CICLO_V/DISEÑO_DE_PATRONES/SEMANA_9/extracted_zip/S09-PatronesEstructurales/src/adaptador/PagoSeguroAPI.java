/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adaptador;

/**
 *
 * Servicio Externo
 */
public class PagoSeguroAPI {
    
    public void realizarPago(double cantidad, String tarjeta){
        System.out.println("Pago realizado con"
                + "pago seguro API : S/."+cantidad+
                "a la tarjeta :"+tarjeta);
    }
}
