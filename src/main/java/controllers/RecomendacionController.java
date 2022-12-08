package controllers;

import cuenta.Cuenta;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class RecomendacionController {
  public ModelAndView getRecomendaciones(Request request, Response response) {
    Cuenta cuenta = request.session().attribute("cuenta");
    /*
    Map<String, Object> model = new HashMap<>();
    model.put("recomendaciones")

     */
    return new ModelAndView(cuenta, "miembroRecomendaciones.hbs");
  }
}
