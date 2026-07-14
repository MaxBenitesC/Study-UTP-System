package infraestructura.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import dominio.modelos.Usuario;

public class UsuarioMapper {
    public static Usuario mapearUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario(
            rs.getString("nombreUsuario"),
            rs.getString("nombre"),
            rs.getString("apellido"),
            rs.getString("password")
        );
        usuario.setPartidasGanadas(rs.getInt("partidasGanadas"));
        usuario.setPartidasPerdidas(rs.getInt("partidasPerdidas"));
        usuario.setPartidasEmpatadas(rs.getInt("partidasEmpatadas"));
        usuario.setEsAdmin(rs.getBoolean("esAdmin"));
        return usuario;
    }
    
}
