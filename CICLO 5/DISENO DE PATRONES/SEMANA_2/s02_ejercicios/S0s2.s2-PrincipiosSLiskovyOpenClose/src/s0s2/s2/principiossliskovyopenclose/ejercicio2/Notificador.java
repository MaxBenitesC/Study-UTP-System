/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package s0s2.s2.principiossliskovyopenclose.ejercicio2;

/**
 *
 * @author c19255
 */
public class Notificador {
    
    public void notificar(Notificacion notificacion, String mensaje){
      notificacion.enviar(mensaje);
    }
}
