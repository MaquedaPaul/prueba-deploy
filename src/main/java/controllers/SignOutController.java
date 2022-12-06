package controllers;

import cuenta.Cuenta;
import cuenta.OrganizacionCuenta;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class SignOutController {
  public ModelAndView logOut(Request request, Response response) {
    request.session().attribute("logged_user", null);
    Cuenta cuenta = request.session().attribute("cuenta");
    cuenta.limpiarSession(request);
    response.redirect("/signin");
    return null;
  }
}
