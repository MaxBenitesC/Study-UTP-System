package presentacion.controladores;

import aplicacion.casosuso.IniciarSesion;
import dominio.modelos.Usuario;
import presentacion.vistas.VistaLogin;
import presentacion.vistas.VistaMenuPrincipal;
import javax.swing.JOptionPane;

public class ControladorLogin {
    private VistaLogin vista;
    private IniciarSesion iniciarSesion;

    public ControladorLogin(VistaLogin vista, IniciarSesion iniciarSesion) {
        this.vista = vista;
        this.iniciarSesion = iniciarSesion;
        this.vista.setControlador(this);
    }

    public void iniciar() {
        vista.mostrar();
    }

    public void login(String correo, String clave) {
        Usuario usuario = iniciarSesion.ejecutar(correo, clave);
        if (usuario != null) {
            JOptionPane.showMessageDialog(null, "Bienvenido " + usuario.getNombreUsuario() + "!");
            
            VistaMenuPrincipal menuPrincipal = new VistaMenuPrincipal();
            menuPrincipal.mostrar();
            
            vista.cerrar();
        } else {
            JOptionPane.showMessageDialog(null, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
