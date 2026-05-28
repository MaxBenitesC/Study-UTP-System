/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package s06.s1.patrónsingletonyprototype.prototype;

/**
 *
 * @author c19255
 */
public class Principal {
    public static void main(String[] args) {
        
        ConcretePrototype prototype1 = new ConcretePrototype("Campo1","Campo2");
        
        System.out.println(""+prototype1);
        
        Prototype prototype2 = new ConcretePrototype(prototype1);
        prototype2.clone();
        System.out.println("Objeto clonado");
        System.out.println(""+prototype2);
        
        System.out.println("Clonando subclase");
        SubClassPrototype subClassPrototype = new SubClassPrototype("field1", "field2", "field3", "field4");
        System.out.println("Subclass Prototype :"+subClassPrototype);
        SubClassPrototype subClassPrototype1 = new SubClassPrototype(subClassPrototype);
        subClassPrototype1.clone();
        System.out.println("Subclass Prototype :"+subClassPrototype1);
        
         
    }
}
