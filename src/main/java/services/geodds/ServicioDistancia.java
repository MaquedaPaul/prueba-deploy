package services.geodds;

import services.geodds.entities.Distancia;

import java.io.IOException;

public interface ServicioDistancia {
   public Distancia distancia(int locOr,
                              String calleO,
                              int altO,
                              int locDes,
                              String calleD,
                              int altDes) throws IOException;
}
