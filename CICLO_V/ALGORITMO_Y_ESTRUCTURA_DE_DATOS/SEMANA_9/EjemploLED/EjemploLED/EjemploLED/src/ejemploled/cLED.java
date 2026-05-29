/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemploled;

public class cLED {
    private cNodo inicio, nuevo, p, q;
    
    public void insertaxInicio(int valor){
        nuevo = new cNodo(valor);
        if ( inicio == null)
            inicio = nuevo;
        else{
            nuevo.setSgte(inicio);
            inicio.setAnt(nuevo);
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
            nuevo.setAnt(p);
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
                if(p.getSgte()!= null){
                    nuevo.setSgte(p.getSgte());
                    p.getSgte().setAnt(nuevo);
                }
                p.setSgte(nuevo);
                nuevo.setAnt(p);
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
                if(p == inicio){
                     nuevo.setSgte(p);
                     p.setAnt(nuevo);
                     inicio = nuevo;
                }
                else{
                    nuevo.setSgte(p);
                    q.setSgte(nuevo);
                    p.setAnt(nuevo);
                    nuevo.setAnt(q);
                }
            }
        }        
    }
    public void eliminaxInicio(){
        if(inicio != null){
            if(inicio.getSgte()== null)
                inicio = inicio.getSgte();
            else{
                inicio = inicio.getSgte();
                inicio.setAnt(null);
            }
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
                q.setSgte(null);p = null;
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
                if(p == inicio){
                    if(inicio.getSgte() == null)
                        inicio = null;
                    else{
                        inicio = inicio.getSgte();
                        inicio.setAnt(null);
                    }
                }
                else{
                    if(p.getSgte()== null){
                        q.setSgte(p.getSgte());
                        p= null;
                    }        
                    else{
                        q.setSgte(p.getSgte());
                        p.getSgte().setAnt(q);p= null;
                    }
                }
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
