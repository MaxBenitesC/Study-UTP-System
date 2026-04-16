package p0033445demoset;
public class P0033445demoSet {
    public static void main(String[] args) {
        RegistrodeVisitantes registro=
                new RegistrodeVisitantes();
        System.out.println("Registro ID 101 (nuevo): "
                + registro.registrar(101));
        System.out.println("Registro ID 102 (nuevo): "
                + registro.registrar(102));
        System.out.println("Registro ID 101 (duplicado): "
                + registro.registrar(101));
    }
}
