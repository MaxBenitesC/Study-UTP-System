package aplicacion.casosuso;

import dominio.modelos.Usuario;
import dominio.puertos.RepositorioUsuario;

public class RegistrarUsuario {
    private final RepositorioUsuario repositorioUsuario;

    public RegistrarUsuario(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    public void ejecutar(Usuario usuario) {
        repositorioUsuario.guardar(usuario);
    }
}
