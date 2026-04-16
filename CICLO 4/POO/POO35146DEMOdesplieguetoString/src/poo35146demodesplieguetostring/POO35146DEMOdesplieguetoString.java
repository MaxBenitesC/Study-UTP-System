package poo35146demodesplieguetostring;

import misClases.Estudiante;

public class POO35146DEMOdesplieguetoString {
    public static void main(String[] args) {
         Estudiante enn=new Estudiante();
         Estudiante jecd=new Estudiante(111,"Javier",true);
         System.out.println(enn);
         System.out.println(jecd);
         System.out.println("cantidad de estudiantes= "+Estudiante.nroEstudiantes);
    }
    
}
