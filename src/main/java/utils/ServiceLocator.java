package utils;

import lombok.Getter;
import lombok.Setter;
import services.geodds.ServicioDistancia;

public class ServiceLocator {

  private static ServiceLocator instance;

  @Getter
  @Setter
  private ServicioDistancia servicioDistancia;

  private ServiceLocator() {
  }

  public static ServiceLocator getInstance() {
    if (instance == null) {
      instance = new ServiceLocator();
    }

    return instance;
  }

}
