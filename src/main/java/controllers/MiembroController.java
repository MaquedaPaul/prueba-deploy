package controllers;

import cuenta.MiembroCuenta;
import cuenta.OrganizacionCuenta;
import repositorios.RepoCuentas;
import exceptions.NoConcuerdaInicioYFin;
import linea.PuntoUbicacion;
import miembro.Miembro;
import organizacion.Organizacion;
import organizacion.Sector;
import organizacion.Solicitud;
import repositorios.RepoMiembros;
import repositorios.RepoOrganizacion;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import transporte.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MiembroController extends AccountController {

  private Miembro obtenerMiembro(Request request){
    MiembroCuenta usuario = request.session().attribute("cuenta");
    Miembro miembro = RepoCuentas.getInstance().obtenerMiembro(usuario);
    return miembro;
  }

  private String obtenerUsuario (Request request){
    MiembroCuenta cuenta = request.session().attribute("cuenta");
    return cuenta.getUsuario();
  }


  public ModelAndView getTrayectos(Request request, Response response) {

    /*comprobarSession(request, response);
    comprobarTipoCuenta(request, response, "miembro");*/
    return new ModelAndView(null,"miembroTrayectos.hbs");
  }

  public ModelAndView pedirVinculacion(Request request, Response response) {
/*    String usuario = comprobarSession(request, response);
    comprobarTipoCuenta(request, response, "miembro");*/

    String organizacionSolicitada = request.queryParams("organizacionSolicitada");
    String sectorSolicitado = request.queryParams("sectorSolicitado");
    Organizacion organizacionObjetivo = RepoOrganizacion.getInstance().getOrganizacionPor(organizacionSolicitada);

    if (organizacionObjetivo == null) {
      response.redirect("/home/vinculacion");
      return null;
    }
    Sector sectorObjetivo = organizacionObjetivo.obtenerSectorPor(sectorSolicitado);
    if (sectorObjetivo == null) {
      response.redirect("/home/vinculacion");
      return null;
    }
    Miembro miembro =  obtenerMiembro(request);
    miembro.solicitarVinculacion(organizacionObjetivo, new Solicitud(miembro, sectorObjetivo));
    RepoOrganizacion.getInstance().agregarOrganizacion(organizacionObjetivo);
    response.redirect("/home");
    return null;
  }

  public ModelAndView getRegistro(Request request, Response response) {
    /*comprobarSession(request, response);
    comprobarTipoCuenta(request, response, "miembro");*/
    return new ModelAndView(null,"registro.hbs");
  }

  public ModelAndView getRegistrarTrayecto(Request request, Response response) {
    /*comprobarSession(request, response);
    comprobarTipoCuenta(request, response, "miembro");*/
    BuilderTrayecto trayecto = request.session().attribute("trayecto");
    if (trayecto == null) {
      request.session().attribute("trayecto", new BuilderTrayecto());
    }
    return new ModelAndView(trayecto, "miembroRegistrarTrayecto.hbs");
  }

  public ModelAndView getVinculacion(Request request, Response response) {
    /*comprobarSession(request, response);
    comprobarTipoCuenta(request, response, "miembro");*/
    List<Organizacion> organizaciones =  RepoOrganizacion.getInstance().getOrganizaciones();
    List<Sector> sectores = new ArrayList<>(RepoOrganizacion.getInstance().obtenerTodosLosSectores());
    HashMap<String, Object> hashMap = new HashMap<>();
    hashMap.put("sectores",sectores);
    hashMap.put("organizaciones", organizaciones);
    return new ModelAndView(hashMap,"miembroVinculacion.hbs");
  }

  public ModelAndView getTrayectoNuevo(Request request, Response response) {
    /*comprobarSession(request, response);
    comprobarTipoCuenta(request, response, "miembro");*/
    return new ModelAndView(null,"miembroTrayectoNuevo.hbs");
  }

  public ModelAndView cargarTramo(Request request, Response response) throws NoConcuerdaInicioYFin {
    /*comprobarSession(request, response);
    comprobarTipoCuenta(request, response, "miembro");*/

    String localidadIdPartidaString = request.queryParams("localidad-partida");
    String callePartida = request.queryParams("calle-partida");
    String alturaPartidaString = request.queryParams("altura-partida");
    String localidadIdLlegadaString = request.queryParams("localidad-llegada");
    String calleLlegada = request.queryParams("calle-llegada");
    String alturaLlegadaString = request.queryParams("altura-llegada");

    Map<String, Object> model = new HashMap<>();
    HashMap<String, Transporte> map = new HashMap<>();
    map.put("Publico", new TransportePublico());
    map.put("Propulcion Humana", new PropulsionHumana());
    BuilderTrayecto trayecto = request.session().attribute("trayecto");
    Transporte transporte;
    PuntoUbicacion puntoPartida;
    PuntoUbicacion puntoLlegada;
    try {
      int alturaLlegada = Integer.parseInt(alturaLlegadaString);
      int localidadIdLlegada = Integer.parseInt(localidadIdLlegadaString);
      int alturaPartida = Integer.parseInt(alturaPartidaString);
      int localidadIdPartida = Integer.parseInt(localidadIdPartidaString);
      transporte = map.get(request.queryParams("tipo-transporte"));
      puntoPartida = new PuntoUbicacion(localidadIdPartida, callePartida, alturaPartida);
      puntoLlegada = new PuntoUbicacion(localidadIdLlegada, calleLlegada, alturaLlegada);

    } catch (Exception e) {
      model.put("tramoIncorecto", true);
      return new ModelAndView(model,"miembroTrayectoNuevo.hbs");
    }

    trayecto.setTransporte(transporte).setPuntoDestino(puntoLlegada);
    try {
      trayecto.setPuntoOrigen(puntoPartida);
    } catch(Exception e) {
      model.put("tramoIncorecto", true);
      return new ModelAndView(model,"miembroTrayectoNuevo.hbs");
    }
    trayecto.agregarTramo();
    request.session().attribute("trayecto", trayecto);
    response.redirect("/home/trayectos/registro");
    return null;
  }

  public ModelAndView eliminarTramo(Request request, Response response) {
    /*comprobarSession(request, response);
    comprobarTipoCuenta(request, response, "miembro");*/
    BuilderTrayecto trayecto = request.session().attribute("trayecto");
    if (!trayecto.getTramos().isEmpty()) {
      trayecto.eliminarUltimoTramo();
    }
    request.session().attribute("trayecto", trayecto);
    response.redirect("/home/trayectos/registro");
    return null;
  }

  public ModelAndView cancelarTrayecto(Request request, Response response) {
    /*comprobarSession(request, response);
    comprobarTipoCuenta(request, response, "miembro");*/
    BuilderTrayecto trayecto = new BuilderTrayecto();
    request.session().attribute("trayecto", trayecto);
    response.redirect("/home/trayectos/registro");
    return null;
  }

  public ModelAndView crearTrayecto(Request request, Response response) {
    /*comprobarSession(request, response);
    comprobarTipoCuenta(request, response, "miembro");*/
    BuilderTrayecto trayecto = request.session().attribute("trayecto");
    Trayecto trayectoNuevo = trayecto.build();

    Map<String, Object> model = new HashMap<>();

    if (trayectoNuevo.getTramos().isEmpty()) {
      //response.redirect("/home/trayectos/registro");
      model.put("trayectoVacio",true);
      return new ModelAndView(model,"miembroRegistrarTrayecto.hbs");
    } else {
      request.session().attribute("trayecto", null);
      Miembro miembro = obtenerMiembro(request);
      miembro.registrarTrayecto(trayectoNuevo);
      RepoMiembros.getInstance().agregarMiembro(miembro);

      model.put("trayectoCargadoConExito",true);
      return new ModelAndView(model,"miembroRegistrarTrayecto.hbs");
      //response.redirect("/home/trayectos/registro");
    }
  }
}
