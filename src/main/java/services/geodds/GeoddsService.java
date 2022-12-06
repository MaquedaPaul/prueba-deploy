package services.geodds;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import services.geodds.entities.Distancia;

public interface GeoddsService {

  @GET("distancia")
  Call<Distancia> distancia(@Header("Authorization") String token,
                            @Query("localidadOrigenId") int locOrigenId,
                            @Query("calleOrigen") String calleOrigen,
                            @Query("alturaOrigen") int altOrigen,
                            @Query("localidadDestinoId") int locDestinoId,
                            @Query("calleDestino") String calleDestino,
                            @Query("alturaDestino") int altDestino);
}
