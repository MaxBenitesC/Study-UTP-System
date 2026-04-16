package Ejercicio_Logistica;

public class InternacionalFactory extends EnvioFactory{
    @Override
    public Envio generarEnvio(String destinatario) {
        return new Internacional(destinatario);
    }
}
