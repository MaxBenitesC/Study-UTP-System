/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package s02.srp.refactorizacion;

import s02.srp.refactorizacion.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author C19255
 */
public class UsuarioRepositorio {
    
  private List<Usuario> usuarios = new ArrayList<>();
    
  
  public void guardarUsuario(Usuario usuario){
      usuarios.add(usuario);
      System.out.println("Usuario Guardado :"+usuario.getNombre());
  
  }
  
  public List<Usuario> obtenerUsuarios(){
     return usuarios;
  }
  
  
  
}
