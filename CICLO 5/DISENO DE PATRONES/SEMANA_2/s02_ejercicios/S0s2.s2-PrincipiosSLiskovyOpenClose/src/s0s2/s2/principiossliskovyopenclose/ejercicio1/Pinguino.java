/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package s0s2.s2.principiossliskovyopenclose.ejercicio1;

/**
 *
 * @author c19255
 */
public class Pinguino extends AveNoVoladora{

    @Override
    public void caminar() {
        System.out.println("Estoy caminando");
    }
    
    @Override
    public void comer(){
      System.out.println("Estoy comiendo pescado");
  } 
    
}
