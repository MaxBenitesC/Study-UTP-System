/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package s02.srp;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author C19255
 */
public class UsuarioManager {
   private Integer id;
   private String nombre;
   private String email;
   private List<UsuarioManager> usuarios = new ArrayList<>();
   
   //Constructor
    public UsuarioManager(Integer id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

  //Lógica de Negocio para validación de correo
    public boolean validarEmail(){
    return email.contains("@") && email.contains(".");
    }
 //Metodos de base de datos silumados con ArrayList
    public void guardarUsuario(){
     if(validarEmail()){
     usuarios.add(this);
     System.out.println("Usuario Guardado "+ this.nombre);
     }else{
         System.out.println("Error : Email no válido");
     }
    }
   public void listarUsuarios(){
       for (UsuarioManager usuario : usuarios) {
           System.out.println("ID :"+ usuario.getId() + 
                   " , Nombre :"+usuario.getNombre());
       } 
   } 
}
