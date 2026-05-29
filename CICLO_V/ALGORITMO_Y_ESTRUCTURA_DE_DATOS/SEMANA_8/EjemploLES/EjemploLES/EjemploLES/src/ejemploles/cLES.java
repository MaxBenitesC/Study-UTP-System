/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemploles;

public class cLES {
    private cNodo inicio, nuevo, p, q;
    
    public void insertaxInicio(int valor){
        nuevo = new cNodo(valor);
        if ( inicio == null)
            inicio = nuevo;
        else{
            nuevo.setSgte(inicio);
            inicio = nuevo;
        }
    }
    public void insertaxFinal(int valor){
        nuevo = new cNodo(valor);
        if ( inicio == null)
            inicio = nuevo;
        else{
            p = inicio;
            while(p.getSgte() != null){
                p = p.getSgte();
            }
            p.setSgte(nuevo);
        }        
    }
    public void insertaDespues(int valor, int valor1){
        nuevo = new cNodo(valor);
        if ( inicio == null)
            inicio = nuevo;
        else{
            p = inicio;
            while(p.getSgte()!= null && p.getDato()!=valor1){
                p = p.getSgte();
            }
            if(p.getDato()==valor1){
                nuevo.setSgte(p.getSgte());
                p.setSgte(nuevo);
            }
        }        
    }
    public void insertaAntes(int valor, int valor1){
        nuevo = new cNodo(valor);
        if ( inicio == null)
            inicio = nuevo;
        else{
            p = inicio;
            while(p.getSgte()!= null && p.getDato()!=valor1){
                q = p;
                p = p.getSgte();
            }
            if(p.getDato()==valor1){
                nuevo.setSgte(p);
                q.setSgte(nuevo);
            }
        }        
    }
    public void eliminaxInicio(){
        if(inicio != null){
            inicio = inicio.getSgte();
        }
    }
    public void eliminaxFinal(){
        if(inicio != null){
            if(inicio.getSgte() == null)
                inicio = null;
            else{
                p= inicio;
                while(p.getSgte()!= null){
                    q = p; p = p.getSgte();
                }
                q.setSgte(null);
            }
        }
    }
    public void eliminaNodoB(int valor){
        if(inicio != null){
            p = inicio;
            while(p.getSgte()!= null && p.getDato()!= valor){
                q = p; p = p.getSgte();
            }
            if(p.getDato()== valor){
                if(p == inicio)
                    inicio = inicio.getSgte();
                else
                    q.setSgte(p.getSgte());
            }
        }
    }
    public String muestraLES(){
        String cadena="";
        if(inicio != null){
            p = inicio;
            while(p != null){
                cadena = cadena + p.getDato()+" - ";
                p = p.getSgte();
            }
        }
        return cadena;
    }    
}
