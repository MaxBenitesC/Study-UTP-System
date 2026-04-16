/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package misClases;

/**
 *
 * @author C00301
 */
public class Estudiante {
    private int codigo;
    private String nombre;
    private static int ctdestudiantes=0;

    public Estudiante() {
        codigo=0;
        nombre="nn";
        ctdestudiantes++;
    }

    public Estudiante(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        ctdestudiantes++;
    }

    @Override
    public String toString() {
        return "Estudiante{" + "codigo=" + codigo
                + ", nombre=" + nombre + '}';
    }
    

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the ctdestudiantes
     */
    public static int getCtdestudiantes() {
        return ctdestudiantes;
    }
}
