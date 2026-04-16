/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package misClases;

/**
 *
 * @author Administrador
 */
public class Rectangulo extends figgeom{
     private double largo;
     private double ancho;

    public Rectangulo() {
        this(2,1);
    }

    public Rectangulo(double largo, double ancho) {
        this.largo = largo;
        this.ancho = ancho;
    }
    
     
    @Override
    public double area() {
        return getLargo()*getAncho();
    }

    @Override
    public String toString() {
        return "Rectangulo{" +super.toString()+ "largo=" + largo + ", ancho=" + ancho + '}';
    }
    

    /**
     * @return the largo
     */
    public double getLargo() {
        return largo;
    }

    /**
     * @param largo the largo to set
     */
    public void setLargo(double largo) {
        this.largo = largo;
    }

    /**
     * @return the ancho
     */
    public double getAncho() {
        return ancho;
    }

    /**
     * @param ancho the ancho to set
     */
    public void setAncho(double ancho) {
        this.ancho = ancho;
    }
    
}
