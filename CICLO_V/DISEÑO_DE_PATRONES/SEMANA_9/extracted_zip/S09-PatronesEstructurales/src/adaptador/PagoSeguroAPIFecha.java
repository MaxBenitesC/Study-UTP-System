/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adaptador;

import java.time.LocalDate;

/**
 *
 * @author c19255
 */
public class PagoSeguroAPIFecha {
    
    public void ejecutarPago(String tarjeta, double cantidad, LocalDate fecha){
        System.out.println("PagoSeguroApiFecha -> Pago de S/. :"+cantidad
        +"a la tarjeta "+tarjeta
        + "en la fecha "+fecha);
    }
}
