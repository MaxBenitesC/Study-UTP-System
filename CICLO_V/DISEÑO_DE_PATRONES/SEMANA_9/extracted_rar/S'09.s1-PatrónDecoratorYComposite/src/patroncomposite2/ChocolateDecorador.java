/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patroncomposite2;

/**
 *
 * @author Admin
 */
public class ChocolateDecorador extends BebidaDecorador{
    
    public ChocolateDecorador(Bebida bebida) {
        super(bebida);
    }
    
    @Override
    public String getDescripcion(){
        return bebida.getDescripcion()+", con chocolate";
    }
    
    @Override
    public double getCosto(){
    
        return bebida.getCosto()+2.5;
    }
}
