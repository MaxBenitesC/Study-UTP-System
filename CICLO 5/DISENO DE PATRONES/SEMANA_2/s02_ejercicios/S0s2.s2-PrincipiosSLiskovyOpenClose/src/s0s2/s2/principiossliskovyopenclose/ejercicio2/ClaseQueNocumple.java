/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package s0s2.s2.principiossliskovyopenclose.ejercicio2;

/**
 *
 * @author c19255
 */
public class ClaseQueNocumple {
    
    
    public void enviar(String tipo, String mensaje){
    
       if(tipo.equals("email")){
           System.out.println("Eviando email :"+mensaje);
       } else if(tipo.equals("sms")){
           System.out.println("Enviando SMS :"+mensaje);
       } else if(tipo.equals("push")){
           System.out.println("Enviando notificación Push :"+mensaje);
       } 
    }
    
}
