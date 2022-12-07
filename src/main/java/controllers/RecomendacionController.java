package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class RecomendacionController {
  public ModelAndView getRecomendaciones(Request request, Response response) {
    return new ModelAndView(null, "miembroRecomendaciones.hbs");
  }
}
