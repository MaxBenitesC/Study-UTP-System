/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package s06.s1.patrónsingletonyprototype.prototype;

/**
 *
 * @author c19255
 */
public class ConcretePrototype implements Prototype {

    protected String field1;
    protected String field2;

    public ConcretePrototype(String field1, String field2) {
        this.field1 = field1;
        this.field2 = field2;
    }

    public ConcretePrototype(ConcretePrototype prototype) {
        this.field1 = prototype.field1;
        this.field2 = prototype.field2;
    }

    @Override
    public Prototype clone() {
        return new ConcretePrototype(this);
    }

    public String getField1() {
        return field1;
    }

    @Override
    public String toString() {
        return "ConcretePrototype{" + "field1=" + field1 + ", field2=" + field2 + '}';
    }

}
