import aplicacion.casosuso.IniciarSesion;
import dominio.puertos.RepositorioUsuario;
import infraestructura.persistencia.RepositorioUsuarioMySQL;
import presentacion.controladores.ControladorLogin;
import presentacion.vistas.VistaLogin;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RepositorioUsuario repositorio = new RepositorioUsuarioMySQL();
            IniciarSesion iniciarSesion = new IniciarSesion(repositorio);
            VistaLogin vista = new VistaLogin();
            ControladorLogin controlador = new ControladorLogin(vista, iniciarSesion);
            controlador.iniciar();
        });
    }
}
