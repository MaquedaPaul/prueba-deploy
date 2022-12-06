package controllers;

import cuenta.Cuenta;
import cuenta.TipoCuenta;
import spark.Request;
import spark.Response;

import java.util.Objects;

public class Validador {

  public static void validarAcceso(Request request, Response response) {
    String url1 = request.url();
    System.out.println(url1);
    if (request.url().endsWith("/signin")|| request.url().endsWith("/signout")) {
      System.out.println("es el signin");
      return;
    }

    System.out.println("no es el signin");

    if (request.session().attribute("cuenta") == null) {
      System.out.println("la session no existe");
      response.redirect("/signin");
      return;
    }

    System.out.println("la session existe");
    Cuenta cuenta = request.session().attribute("cuenta");

    if (!cuenta.puedeAccederA(request.url())) {
      response.redirect("/home");
      System.out.println("no tengo permiso");
      return;
    }
    System.out.println("tengo permiso");

  }

}
