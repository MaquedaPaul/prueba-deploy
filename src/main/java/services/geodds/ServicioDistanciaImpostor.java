package services.geodds;

import services.geodds.entities.Distancia;

import java.io.IOException;

public class ServicioDistanciaImpostor implements ServicioDistancia {

  @Override
  public Distancia distancia(int locOr, String calleO, int altO, int locDes, String calleD, int altDes) throws IOException {
    return new Distancia(10D);
  }
}
