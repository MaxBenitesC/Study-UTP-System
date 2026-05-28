/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prototype;

/**
 *
 * @author c19255
 */
public class ConcretePrototype implements Prototype{

    protected String nombre;
    protected String apellido;
    protected String correo;
    protected String dni;
    protected String celular;

    public ConcretePrototype(String nombre, String apellido, String correo, String dni, String celular) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.dni = dni;
        this.celular = celular;
    }
    
    
    @Override
    public Prototype clone() {
       return new ConcretePrototype(this.nombre, this.apellido, this.correo, this.dni, this.celular);
    }

    @Override
    public String toString() {
        return "ConcretePrototype{" + "nombre=" + nombre + ", apellido=" + apellido + ", correo=" + correo + ", dni=" + dni + ", celular=" + celular + '}';
    }
    
    
}
