package LaAventuraDelSabio;

import InterfaceAbstracta.*;

/**
 * Representa a un sabio del elemento Fuego en el juego.
 * Hereda de la clase abstracta {@link Sabio} y personaliza el ataque aumentando el daño base.
 */
public class SabioFuego extends Sabio {

    /**
     * Constructor del sabio de fuego.
     *
     * @param nombre Nombre del personaje.
     */
    public SabioFuego(String nombre) {
        super(nombre, "Fuego", 100, 5);
    }

    /**
     * Realiza un ataque especial tipo fuego al objetivo,
     * incrementando el daño base en 5 puntos.
     *
     * @param objetivo  Personaje al que se le realiza el ataque.
     * @param danoBase  Valor base del daño a aplicar antes del ajuste.
     */
    @Override
    public void atacar(Personaje objetivo, int danoBase) {
        int danoAjustado = danoBase + 5;
        objetivo.recibirDano(danoAjustado);
        System.out.println("🔥 ¡" + getNombre() + " (Fuego) usó Erupción! " +
                "Causó " + danoAjustado + " de daño a " + objetivo.getNombre() + ".");
    }
}