/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package misClases;

/**
 *
 * @author Administrador
 */
public class Estudiante extends Persona {
    private int creditos;
    private double prompond;
    private static int ctdestudiantes;

    public Estudiante() {
        ctdestudiantes++;
    }
    public Estudiante(int creditos, double prompond) {
        this.creditos = creditos;
        this.prompond = prompond;
        ctdestudiantes++;
    }
    public Estudiante(int creditos, double prompond, String nombre, boolean sexo) {
        super(nombre, sexo);
        this.creditos = creditos;
        this.prompond = prompond;
    }

    @Override
    public String toString() {
        return "Estudiante{" + super.toString()+ "creditos=" + creditos + ", prompond=" + prompond + '}';
    }

    /**
     * @return the creditos
     */
    public int getCreditos() {
        return creditos;
    }

    /**
     * @param creditos the creditos to set
     */
    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    /**
     * @return the prompond
     */
    public double getPrompond() {
        return prompond;
    }

    /**
     * @param prompond the prompond to set
     */
    public void setPrompond(double prompond) {
        this.prompond = prompond;
    }

    /**
     * @return the ctdestudiantes
     */
    public static int getCtdestudiantes() {
        return ctdestudiantes;
    }
    
}
