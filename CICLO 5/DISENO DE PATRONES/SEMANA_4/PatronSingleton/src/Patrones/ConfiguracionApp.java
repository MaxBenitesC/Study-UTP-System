package Patrones;

public class ConfiguracionApp {
    private static ConfiguracionApp instancia;
    private String urlBase;
    private String username;
    private String password;

    public ConfiguracionApp() {
        this.urlBase="http://localhost:8080";
        this.username="admin";
        this.password="1234";
    }

    //Metodo estatico para obtener la instancia unica
    public static ConfiguracionApp getInstancia(){
        if (instancia==null){
         instancia = new ConfiguracionApp();
        }
        return instancia;
    }

}
