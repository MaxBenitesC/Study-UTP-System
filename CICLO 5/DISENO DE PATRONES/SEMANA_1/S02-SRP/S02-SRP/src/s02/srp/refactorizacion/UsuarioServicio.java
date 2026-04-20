/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package s02.srp.refactorizacion;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author C19255
 */
public class UsuarioServicio {
    
    
    private List<Usuario> usuarios;
    private UsuarioRepositorio repositorio;

    public UsuarioServicio() {
        this.repositorio = new UsuarioRepositorio();
        usuarios = new ArrayList<>();
    }
    
    public boolean validarEmail(String email){
        return email.contains("@") && email.contains(".");
    }
    
    public void registrarUsuario(String nombre, String email){
        if(validarEmail(email)){
        Usuario usuario = new Usuario(nombre, email);
        repositorio.guardarUsuario(usuario);            
        }else{
            
            System.out.println("Correo errado");
        }   
    }
    
    public void listarUsuarios(){
        usuarios = repositorio.obtenerUsuarios();
        for (Usuario usuario : usuarios) {
            System.out.println("Usuario :"+usuario.getNombre()+ " Correo :"+usuario.getEmail());
        }
    }
    
}
