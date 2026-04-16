package InterfaceAbstracta;

/**
 * Clase abstracta que representa a un Sabio dentro del juego.
 * <p>
 * Un Sabio es un tipo específico de {@link Personaje} que posee un
 * atributo adicional llamado nivel de sabiduría. Las subclases como
 * SabioFuego, SabioAgua y SabioTierra heredan esta estructura y
 * definen su comportamiento de ataque.
 * </p>
 */
public abstract class Sabio extends Personaje {

    /**
     * Nivel de sabiduría del sabio. Este atributo puede diferenciar
     * las habilidades especiales o estadísticas adicionales de los Sabios.
     */
    private int nivelSabiduria;

    /**
     * Constructor protegido que inicializa un Sabio con sus atributos básicos.
     *
     * @param nombre      nombre del sabio.
     * @param rol         rol o elemento del sabio (por ejemplo: "Fuego", "Agua", "Tierra").
     * @param psInicial   puntos de salud iniciales del sabio.
     * @param sabiduria   nivel de sabiduría del sabio.
     */
    protected Sabio(String nombre, String rol, int psInicial, int sabiduria) {
        super(nombre, rol, psInicial);
        this.nivelSabiduria = sabiduria;
    }

    /**
     * Obtiene el nivel de sabiduría del Sabio.
     *
     * @return el nivel de sabiduría del personaje.
     */
    public int getNivelSabiduria() {
        return nivelSabiduria;
    }
}
