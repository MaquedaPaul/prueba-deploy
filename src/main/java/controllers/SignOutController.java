package controllers;

import cuenta.Cuenta;
import cuenta.OrganizacionCuenta;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class SignOutController {
  public ModelAndView logOut(Request request, Response response) {

    Cuenta cuenta = request.session().attribute("cuenta");
    if (cuenta == null) {
      response.redirect("/signin");
      return null;
    }
    cuenta.limpiarSession(request);
    response.redirect("/signin");
    return null;
  }
}
