/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package facade;

/**
 *
 * @author c19255
 */
public class Amplifier {
    public void on(){
        System.out.println("Amplificador encendido");
    }
    public void setDVD(DVDPlayer dvd){
        System.out.println("Conectado al DVD");
    }
    public void setVolume(int level){
        System.out.println("Volumen ajustado a :"+level);
    }
    public void off(){
        System.out.println("DVD player apagado");
    }
}
