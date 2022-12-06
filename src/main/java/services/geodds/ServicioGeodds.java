package services.geodds;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import services.geodds.configuraciones.Configuracion;
import services.geodds.entities.Distancia;

import java.io.IOException;

public class ServicioGeodds implements ServicioDistancia {
  private static ServicioGeodds instancia = null;
  private Retrofit retrofit;
  private static final String urlAPI = Configuracion.getUrlApi();
  private static final String token = Configuracion.getToken();

  private ServicioGeodds() {
    this.retrofit = new Retrofit.Builder()
        .baseUrl(urlAPI)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  public static ServicioGeodds getInstancia() {
    if (instancia == null) {
      instancia = new ServicioGeodds();
    }
    return instancia;
  }

  public Distancia distancia(int locOr,
                             String calleO,
                             int altO,
                             int locDes,
                             String calleD,
                             int altDes)
      throws IOException {
    GeoddsService geoddsService = this.retrofit.create(GeoddsService.class);
    Call<Distancia> requestDistancia = geoddsService
        .distancia(token, locOr, calleO, altO, locDes, calleD, altDes);
    Response<Distancia> responseDistancia = requestDistancia.execute();
    //System.out.println("Codigo de Respuesta: " + responseDistancia.code());
    return responseDistancia.body();
  }

}
