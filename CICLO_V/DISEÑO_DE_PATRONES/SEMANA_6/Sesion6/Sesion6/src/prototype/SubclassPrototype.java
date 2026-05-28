/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prototype;

/**
 *
 * @author c19255
 */
public class SubclassPrototype extends ConcretePrototype{
    
    private String sexo;
    private Integer edad;
    private String grupoSanguineo;

    public SubclassPrototype(String nombre, String apellido, String correo, String dni, String celular,String sexo, Integer edad, String grupoSanguineo) {
        super(nombre, apellido, correo, dni, celular);
        this.sexo = sexo;
        this.edad = edad;
        this.grupoSanguineo = grupoSanguineo;
    }


    @Override
    public Prototype clone() {
        return new SubclassPrototype(nombre, apellido, correo, dni, celular, sexo, edad, grupoSanguineo);
   }

    @Override
    public String toString() {
        return "SubclassPrototype{"+super.toString() + "sexo=" + sexo + ", edad=" + edad + ", grupoSanguineo=" + grupoSanguineo + '}';
    }


    
    
    
    
}
