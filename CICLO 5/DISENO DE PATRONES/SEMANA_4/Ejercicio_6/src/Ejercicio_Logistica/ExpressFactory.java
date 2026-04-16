package Ejercicio_Logistica;

public class ExpressFactory extends EnvioFactory{
    @Override
    public Envio generarEnvio(String destinatario) {
        return new Express(destinatario);
    }
}
