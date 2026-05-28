/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package facade;

/**
 *
 * @author c19255
 */
public class HomeTheater {
    private DVDPlayer dvd;
    private Proyector proyector;
    private Amplifier amplifier;

    public HomeTheater(DVDPlayer dvd, Proyector proyector, Amplifier amplifier) {
        this.dvd = dvd;
        this.proyector = proyector;
        this.amplifier = amplifier;
    }
    
    public void watchMovie(String movie){
        System.out.println("Prepara el sistema para ver la pelicula :"+movie);
        proyector.on();
        proyector.play();
        amplifier.on();
        amplifier.setDVD(dvd);
        dvd.on();
        dvd.play();  
    }
    
}
