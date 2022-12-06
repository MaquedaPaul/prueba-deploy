package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class RaizController {
  public ModelAndView getPage(Request request, Response response) {
    if (request.session().attribute("cuenta") != null) {
      response.redirect("/home");
    }
    return new ModelAndView(null, "raiz.hbs");
  }
}
