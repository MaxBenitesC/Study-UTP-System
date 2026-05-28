/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package s06.s1.patrónsingletonyprototype.prototype;

/**
 *
 * @author c19255
 */
public class SubClassPrototype extends ConcretePrototype{
    
    private String field3;
    private String field4;

    public SubClassPrototype(String field1,String field2,String field3,String field4) {
       super(field1,field2);
        this.field3 = field3;
        this.field4 = field4;
    }

    public SubClassPrototype(SubClassPrototype prototype) {
        super(prototype.field1,prototype.field2);
        this.field3=prototype.field3;
        this.field4=prototype.field4;
    }
    
    @Override
    public Prototype clone(){
       return new SubClassPrototype(this);
    }

    public String getField3() {
        return field3;
    }

    public String getField4() {
        return field4;
    }

    public String getField1() {
        return field1;
    }

    public String getField2() {
        return field2;
    }


    @Override
    public String toString() {
        return "SubClassPrototype{"+super.field1+super.field2 + "field3=" + field3 + ", field4=" + field4 + '}';
    }

   
}
