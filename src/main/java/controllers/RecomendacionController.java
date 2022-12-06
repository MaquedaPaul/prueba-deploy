package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class RecomendacionController {
  public ModelAndView getRecomendaciones(Request request, Response response) {
    if (request.session().attribute("logged_user") == null) {
      response.redirect("/signin");
    }
    return new ModelAndView(null, "miembroRecomendaciones.hbs");
  }
}
