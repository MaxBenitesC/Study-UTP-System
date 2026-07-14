package aplicacion;

import dominio.modelos.Usuario;

public class Sesion {
    private static Sesion instancia;
    private Usuario usuario;

    private Sesion() {}

    private static synchronized Sesion getInstancia() {
        if (instancia == null) {
            instancia = new Sesion();
        }
        return instancia;
    }

    public static void iniciarSesion(Usuario usuario) {
        getInstancia().usuario = usuario;
    }

    public static Usuario usuario() {
        return getInstancia().usuario;
    }

    public void cerrarSesion() {
        this.usuario = null;
    }
}
