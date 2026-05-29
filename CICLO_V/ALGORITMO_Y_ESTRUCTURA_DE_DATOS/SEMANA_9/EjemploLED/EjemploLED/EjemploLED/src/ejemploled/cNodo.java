/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemploled;
public class cNodo {
    private int dato;
    private cNodo sgte,ant;

    public cNodo getAnt() {
        return ant;
    }
    public void setAnt(cNodo ant) {
        this.ant = ant;
    }
    public cNodo(int dato) {
        this.dato = dato;
    }
    public int getDato() {
        return dato;
    }
    public void setDato(int dato) {
        this.dato = dato;
    }
    public cNodo getSgte() {
        return sgte;
    }
    public void setSgte(cNodo sgte) {
        this.sgte = sgte;
    }
}
