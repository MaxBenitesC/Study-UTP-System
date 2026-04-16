package LaAventuraDelSabio;

import InterfaceAbstracta.*;

/**
 * Representa a un sabio del elemento Tierra en el juego.
 * Hereda de {@link Sabio} y define un ataque personalizado que aumenta el daño base.
 */
public class SabioTierra extends Sabio {

    /**
     * Constructor del sabio de tierra.
     *
     * @param nombre Nombre del personaje sabio.
     */
    public SabioTierra(String nombre) {
        super(nombre, "Tierra", 90, 6);
    }

    /**
     * Realiza un ataque especial tipo tierra al personaje objetivo.
     * El ataque incrementa el daño base en 2 puntos.
     *
     * @param objetivo  Personaje que recibe el ataque.
     * @param danoBase  Daño base a aplicar antes del ajuste.
     */
    @Override
    public void atacar(Personaje objetivo, int danoBase) {
        int danoAjustado = danoBase + 2;
        objetivo.recibirDano(danoAjustado);
        System.out.println("⛰️ ¡" + getNombre() + " (Tierra) usó Roca " +
                "Sólida! Causó " + danoAjustado + " de daño a " + objetivo.getNombre() + ".");
    }
}