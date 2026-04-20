/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package s0s2.s2.principiossliskovyopenclose.ejercicio1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author c19255
 */
public class Principal {

    public static void main(String[] args) {

        Ave aguila = new Aguila();
        Ave paloma = new Paloma();
        Ave pinguino = new Pinguino();

        List<Ave> aves = new ArrayList<>();
        aves.add(aguila);
        aves.add(paloma);
        aves.add(pinguino);
        for (Ave ave : aves) {
            ave.comer();
        }

    }
}
