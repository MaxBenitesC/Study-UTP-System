package InterfaceAbstracta;

/**
 * La interfaz {@code Atacante} define el comportamiento de ataque que deben implementar
 * todos los personajes que pueden atacar en el juego.
 */
public interface Atacante {

    /**
     * Realiza un ataque a un personaje objetivo, infligiéndole un daño específico.
     *
     * @param objetivo el personaje que recibirá el ataque.
     * @param danoBase la cantidad base de daño que se aplicará al objetivo.
     */
    void atacar(Personaje objetivo, int danoBase);
}