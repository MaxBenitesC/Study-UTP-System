/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patroncomposite2;

/**
 *
 * @author Admin
 */
public class LecheDecorador extends BebidaDecorador{

    public LecheDecorador(Bebida bebida) {
       super(bebida);
    }
    
    @Override
    public String getDescripcion(){
        return bebida.getDescripcion()+", con leche";
    }
    
    @Override
    public double getCosto(){
    
        return bebida.getCosto()+1.5;
    }
}
