package controllers;

import cuenta.Cuenta;
import spark.Request;
import spark.Response;

import static spark.Spark.halt;

public class Validador {

  public static void validarAcceso(Request request, Response response) {
    String url1 = request.url();
    System.out.println("RUTA ACCEDIDA: " + url1);
    if (request.url().endsWith("/signin")|| request.url().endsWith("/signout")) {
      System.out.println("ACCEDI AL SIGNIN");
      return;
    }

    if (request.url().endsWith("/favicon.ico")) {
      System.out.println("IGNORO EL FAVICON");
      return;
    }

    if (request.session().attribute("cuenta") == null) {
      System.out.println("NO ESTABA LOGUEADO");
      System.out.println("REDIRECCIONO AL SIGNIN");
      //TODO AGREGAR PANTALLA 404 NOT FOUND
      halt(402,"TOMATELAS QUE HACES");
      response.redirect("/signin");
      return;
    }

    System.out.println("ESTO LOGUEADO");
    Cuenta cuenta = request.session().attribute("cuenta");

    if (!cuenta.puedeAccederA(request.url())) {
      System.out.println("NO PUEDO ACCEDER");
      System.out.println("REDIRECCIONO AL NOT FOUND");
      //TODO AGREGAR PANTALLA 404 NOT FOUND
      halt(402,"TOMATELAS QUE HACES");
      response.redirect("/home");
      return;
    }
    System.out.println("PUEDO ACCEDER");

  }

}
