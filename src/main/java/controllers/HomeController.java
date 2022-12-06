package controllers;

import cuenta.Cuenta;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HomeController {
  public ModelAndView getHome(Request request, Response response) {
    Cuenta cuenta = request.session().attribute("cuenta");
    if (cuenta == null) {
      response.redirect("/signin");
      return null;
    }

    cuenta.guardarEnSesion(request);

    return new ModelAndView(cuenta.datosDelHome(request), cuenta.home());
  }
}
