/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prototype;

/**
 *
 * @author c19255
 */
public class PrototypeMain {
    public static void main(String[] args) {
        
        Prototype prototype1= new ConcretePrototype("nombre1", "apellido1", "correo1", "dni1", "celular1");
        Prototype prototype2=prototype1.clone();
        System.out.println("Prototype :"+prototype1);
        System.out.println("Prototype :"+prototype2);
     
        SubclassPrototype subclassPrototype= new SubclassPrototype("nombre2", "apellido2", "correo2", "dni2", "celular2", "sexo2", 24, "O+");
        
        SubclassPrototype subclassPrototype1= (SubclassPrototype)subclassPrototype.clone();
        
        System.out.println(""+subclassPrototype);
        System.out.println(""+subclassPrototype1);
        
    }  
     
}
