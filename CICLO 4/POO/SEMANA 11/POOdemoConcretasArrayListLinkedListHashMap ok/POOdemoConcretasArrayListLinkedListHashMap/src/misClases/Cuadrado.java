/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misClases;

/**
 *
 * @author USER
 */
public class Cuadrado extends Rectangulo {
    public Cuadrado() {
        super(1,1);
    }
    public Cuadrado(double lado) {
        super(lado,lado);
    }
    public Cuadrado(double lado, String color, boolean relleno) {
        super(lado, lado, color, relleno);
    }
}
