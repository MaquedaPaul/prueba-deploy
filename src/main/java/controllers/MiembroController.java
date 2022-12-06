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
    String usuario = obtenerUsuario(request);
    String organizacionSolicitada = request.queryParams("organizacionSolicitada");
    String sectorSolicitado = request.queryParams("sectorSolicitado");
    Organizacion organizacionObjetivo = RepoOrganizacion.getInstance().getOrganizacionPor(organizacionSolicitada);

    if (organizacionObjetivo == null) {
      response.redirect("/home/vinculacion");
    }
    Sector sectorObjetivo = organizacionObjetivo.obtenerSectorPor(sectorSolicitado);
    if (sectorObjetivo == null) {
      response.redirect("/home/vinculacion");
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

    int localidadIdPartida = Integer.parseInt(request.queryParams("localidad-partida"));
    String callePartida = request.queryParams("calle-partida");
    int alturaPartida = Integer.parseInt(request.queryParams("altura-partida"));
    int localidadIdLlegada = Integer.parseInt(request.queryParams("localidad-llegada"));
    String calleLlegada = request.queryParams("calle-llegada");
    int alturaLlegada = Integer.parseInt(request.queryParams("altura-llegada"));
    PuntoUbicacion puntoPartida = new PuntoUbicacion(localidadIdPartida, callePartida, alturaPartida);
    PuntoUbicacion puntoLlegada = new PuntoUbicacion(localidadIdLlegada, calleLlegada, alturaLlegada);

    HashMap<String, Transporte> map = new HashMap<>();
    map.put("Publico", new TransportePublico());
    map.put("Propulcion Humana", new PropulsionHumana());

    Transporte transporte = map.get(request.queryParams("tipo-transporte"));
    BuilderTrayecto trayecto = request.session().attribute("trayecto");
    trayecto.setTransporte(transporte).setPuntoDestino(puntoLlegada);
    try {
      trayecto.setPuntoOrigen(puntoPartida);
    } catch(Exception e) {
      response.redirect("/home/trayectos/registro/tramo-nuevo");
      return null;
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
    request.session().attribute("trayecto", null);
    Miembro miembro = obtenerMiembro(request);
    miembro.registrarTrayecto(trayectoNuevo);
    RepoMiembros.getInstance().agregarMiembro(miembro);
    miembro = obtenerMiembro(request);
    response.redirect("/home/trayectos/registro");
    return null;
  }
}
