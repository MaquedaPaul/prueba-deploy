package controllers;

import spark.Request;
import spark.Response;

public class AccountController {
  public static String comprobarSession(Request request, Response response) {
    String usuario = request.session().attribute("logged_user");
    if (usuario == null ) {
      response.redirect("/signin");
      return null;
    }
    return usuario;
  }

  public static void comprobarTipoCuenta(Request request,  Response response, String tipo) {
    String tipoUsuario = request.session().attribute("user-type");
    if (!tipoUsuario.equals(tipo)) {
      response.redirect("/home");
    }
  }
}
