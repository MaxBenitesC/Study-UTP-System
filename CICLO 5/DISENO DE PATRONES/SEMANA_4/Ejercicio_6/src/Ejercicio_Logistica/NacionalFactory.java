package Ejercicio_Logistica;

public class NacionalFactory extends EnvioFactory{
    @Override
    public Envio generarEnvio(String destino) {
        return new Nacional(destino);
    }
}
