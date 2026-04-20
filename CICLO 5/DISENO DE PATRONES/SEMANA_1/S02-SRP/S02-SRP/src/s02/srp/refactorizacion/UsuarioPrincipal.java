/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package s02.srp.refactorizacion;

import java.util.Scanner;

/**
 *
 * @author C19255
 */
public class UsuarioPrincipal {
    
    public static void main(String[] args) {
    // Crear 2 usuarios e ingresarlos a la base 
    // de datos y luegos listar los usuarios
    // Ingrese los datos por el teclado    
        Scanner teclado = new Scanner(System.in);
        
        System.out.println("Ingresa los datos el usuario 1");
        System.out.println("Ingresar nombre");
        String nombre1 = teclado.nextLine();
        System.out.println("Ingresar correo");
        String correo1 = teclado.nextLine();
        
        UsuarioServicio servicio = new UsuarioServicio();
        servicio.registrarUsuario(nombre1, correo1);
    
        servicio.listarUsuarios();
    }   
    
}
