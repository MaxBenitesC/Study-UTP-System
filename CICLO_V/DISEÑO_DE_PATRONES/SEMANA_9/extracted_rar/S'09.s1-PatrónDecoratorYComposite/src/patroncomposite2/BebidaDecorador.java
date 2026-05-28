/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patroncomposite2;

/**2
 *
 * @author Admin
 */
public abstract class BebidaDecorador implements Bebida{
    
    protected Bebida bebida;

    public BebidaDecorador(Bebida bebida) {
        this.bebida = bebida;
    }

    @Override
    public String getDescripcion() {
        return bebida.getDescripcion();
    }
    @Override
    public double getCosto() {
       return bebida.getCosto();
    }
    
}
