/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patroncomposite2;

/**
 *
 * @author Admin
 */
public class Principal {
    
    public static void main(String[] args) {
        
        
        Bebida cafe = new CafeSimple();
        System.out.println(""+cafe.getDescripcion()+ " $"+cafe.getCosto());
        
        Bebida cafeConLeche = new LecheDecorador(cafe);
        System.out.println(""+cafeConLeche.getDescripcion()+ " $"+cafeConLeche.getCosto());
        
        
    }
}
