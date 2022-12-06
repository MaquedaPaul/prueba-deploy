package services.geodds.configuraciones;

public class Configuracion {
  private static final String urlAPI = "https://ddstpa.com.ar/api/";
  private static final String token = "Bearer EwiISomWLyg7BW3gnFM0T8Ldj1T7ZMLtaZiaXCnHkJ0=";

  public static String getToken() {
    return token;
  }

  public static String getUrlApi() {
    return urlAPI;
  }
}
