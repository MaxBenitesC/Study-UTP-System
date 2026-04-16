package Ejercicio_cafeteria;

public class main {
    public static void main(String[] args) {

        //Preparar Cafe
        BebidaFactory bebidaCafe = new CafeFactory();
        Bebida bebida1 = bebidaCafe.crearBebida();
        bebida1.preparar();

        //Preparar Jugo
        BebidaFactory bebidaJugo = new JugoFactory();
        Bebida bebida2 = bebidaJugo.crearBebida();
        bebida2.preparar();

        //Preparar Cafe
        BebidaFactory bebidaTe = new TeFactory();
        Bebida bebida3 = bebidaTe.crearBebida();
        bebida3.preparar();
    }
}
