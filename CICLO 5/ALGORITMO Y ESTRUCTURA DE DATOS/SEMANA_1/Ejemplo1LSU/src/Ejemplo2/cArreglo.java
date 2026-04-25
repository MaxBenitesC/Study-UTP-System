/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejemplo2;

/**
 *
 * @author Administrador
 */
public class cArreglo {
    private String nombres[];
    private int edad[];

    public cArreglo(String[] nombres) {
        this.nombres = nombres;
        edad= new int[nombres.length];
        
    }
    public void llenaArreglo(){
        int indMax= edad.length-1;
        int desde = 15, hasta=95;
        for (int i = 0; i <= indMax; i++) {
            edad[i]=(int)(Math.random()*(hasta-desde+1)+desde);
        }
    }
    public String muestraArreglo(){
        String cadena="";
        int indMax=edad.length-1;
        for (int i = 0; i <= indMax; i++) {
            cadena+= nombres[i]+"\t"+edad[i]+"\n";
        }
        return cadena;
    }
}
