package server;

import services.geodds.ServicioDistanciaImpostor;
import spark.Spark;
import utils.ServiceLocator;

public class Server {
  public static void main(String[] args) {
    Spark.port(9000);
    ServiceLocator.getInstance().setServicioDistancia(new ServicioDistanciaImpostor());
    Router.init();
    Spark.init();
  }
}
