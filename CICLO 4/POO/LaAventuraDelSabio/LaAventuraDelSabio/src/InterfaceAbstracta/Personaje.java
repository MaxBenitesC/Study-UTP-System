package InterfaceAbstracta;

/**
 * Clase abstracta que representa un personaje dentro del juego.
 * Todos los personajes tienen un nombre, un rol, puntos de salud (PS) y experiencia (XP),
 * y deben implementar el comportamiento de ataque definido por la interfaz {@link Atacante}.
 */
public abstract class Personaje implements Atacante {
    private String nombre;
    private String rol;
    private int ps;
    private int xp;

    /**
     * Constructor protegido para inicializar un personaje con nombre, rol y puntos de salud iniciales.
     *
     * @param nombre el nombre del personaje.
     * @param rol el rol del personaje (por ejemplo: "Sabio de Agua").
     * @param psInicial los puntos de salud iniciales.
     */
    protected Personaje(String nombre, String rol, int psInicial) {
        this.nombre = nombre;
        this.rol = rol;
        this.ps = psInicial;
        this.xp = 0;
    }

    /**
     * Método abstracto que debe ser implementado por las subclases para definir
     * el comportamiento del ataque hacia otro personaje.
     *
     * @param objetivo el personaje objetivo del ataque.
     * @param danoBase la cantidad base de daño que se infligirá.
     */
    @Override
    public abstract void atacar(Personaje objetivo, int danoBase);

    /**
     * Suma puntos de experiencia al personaje.
     *
     * @param valor la cantidad de experiencia que se sumará.
     */
    public void sumarXp(int valor) {
        this.xp += valor;
    }

    /**
     * Aplica daño al personaje, reduciendo sus puntos de salud.
     * Si los puntos de salud quedan por debajo de cero, se establecen en cero.
     *
     * @param valor cantidad de daño recibido.
     */
    public void recibirDano(int valor) {
        this.ps -= valor;
        if (this.ps < 0) {
            this.ps = 0;
        }
    }

    /**
     * Verifica si el personaje aún está vivo.
     *
     * @return {@code true} si tiene puntos de salud mayores a cero, de lo contrario {@code false}.
     */
    public boolean estaVivo() {
        return this.ps > 0;
    }

    /**
     * Obtiene el nombre del personaje.
     *
     * @return el nombre del personaje.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el rol del personaje.
     *
     * @return el rol del personaje.
     */
    public String getRol() {
        return rol;
    }

    /**
     * Obtiene los puntos de salud del personaje.
     *
     * @return los puntos de salud.
     */
    public int getPs() {
        return ps;
    }

    /**
     * Obtiene los puntos de experiencia del personaje.
     *
     * @return los puntos de experiencia.
     */
    public int getXp() {
        return xp;
    }
}