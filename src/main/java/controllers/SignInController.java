package controllers;

import cuenta.Cuenta;
import repositorios.RepoCuentas;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SignInController {
  private boolean comprobarSesionActiva(Request request){
    return request.session().attribute("cuenta") != null;
  }

  public ModelAndView getSignIn(Request request, Response response) {
    if(comprobarSesionActiva(request)){
      response.redirect("/home");
      return null;
    }
    return new ModelAndView(null, "signin.hbs");
  }

  public ModelAndView logIn(Request request, Response response) {
    String userQuery = request.queryParams("user");
    String userQueryPassword = request.queryParams("password");
    System.out.println("ingreso: " + userQuery + userQueryPassword);

    Cuenta cuenta = RepoCuentas.getInstance().accountByUsername(userQuery);

    if (cuenta == null || !Objects.equals(userQueryPassword, cuenta.getPassword())) {
      System.out.println("ingreso: " + userQuery + userQueryPassword);
      Map<String, Object> model = new HashMap<>();
      model.put("usuarioInexistente",true);
      return new ModelAndView(model,"signin.hbs");
    }

    cuenta.guardarEnSesion(request);
    response.redirect("/home");
    return null;
  }
}
