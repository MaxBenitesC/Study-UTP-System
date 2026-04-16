package LaAventuraDelSabio;

import InterfaceAbstracta.*;

/**
 * Representa a un sabio del elemento Agua en el juego.
 * Hereda de la clase abstracta {@link Sabio} e implementa el método de ataque personalizado.
 */
public class SabioAgua extends Sabio {

    /**
     * Constructor del sabio de agua.
     *
     * @param nombre Nombre del personaje.
     */
    public SabioAgua(String nombre) {
        super(nombre, "Agua", 70, 7);
    }

    /**
     * Realiza un ataque especial tipo agua al objetivo, reduciendo el daño base en 2 puntos.
     *
     * @param objetivo  Personaje al que se le realiza el ataque.
     * @param danoBase  Valor base del daño a aplicar antes del ajuste.
     */
    @Override
    public void atacar(Personaje objetivo, int danoBase) {
        int danoAjustado = danoBase - 2;
        objetivo.recibirDano(danoAjustado);
        System.out.println("💧 ¡" + getNombre() + " (Agua) usó Chorro de" +
                " Agua! Causó " + danoAjustado + " de daño a " + objetivo.getNombre() + ".");
    }
}