package server;

import services.geodds.ServicioDistanciaImpostor;
import spark.Spark;
import utils.ServiceLocator;

public class Server {
  public static void main(String[] args) {
    Spark.port(getHerokuAssignedPort());
    ServiceLocator.getInstance().setServicioDistancia(new ServicioDistanciaImpostor());
    Router.init();
    Spark.init();
  }
  static int getHerokuAssignedPort() {
    ProcessBuilder processBuilder = new ProcessBuilder();
    if (processBuilder.environment().get("PORT") != null) {
      return Integer.parseInt(processBuilder.environment().get("PORT"));
    }
    return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
  }


}
