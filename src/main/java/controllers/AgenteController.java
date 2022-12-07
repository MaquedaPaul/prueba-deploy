package controllers;

import cuenta.AgenteCuenta;

import global.Unidad;
import organizacion.Organizacion;

import organizacion.TipoOrganizacion;

import organizacion.periodo.PeriodoAnual;
import organizacion.periodo.PeriodoMensual;
import repositorios.RepoOrganizacion;
import reporte.ReporteOrganizaciones;
import reporte.ReporteSectorTerritorial;
import reporte.itemsreportes.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import territorio.AgenteTerritorial;
import territorio.SectorTerritorial;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AgenteController {

  private final DateTimeFormatter formatoFecha = new DateTimeFormatterBuilder()
        .appendPattern("yyyy-MM")
        .parseDefaulting(ChronoField.DAY_OF_MONTH, 15)
        .toFormatter();

  private final ReporteSectorTerritorial genReporte = new ReporteSectorTerritorial();

  private PeriodoMensual getPeriodoInicio(Request request) {
    return new PeriodoMensual(LocalDate.parse(request.queryParams("inicio"),formatoFecha));
  }

  private PeriodoMensual getPeriodoFin(Request request) {
    return new PeriodoMensual(LocalDate.parse(request.queryParams("fin"),formatoFecha));
  }

  //RUTAS

  public ModelAndView getComposicionHc(Request request, Response response) {

    AgenteCuenta cuentaUser = request.session().attribute("cuenta");

    return new ModelAndView(cuentaUser,"agenteComposicionHCConsulta.hbs");
  }

  public ModelAndView getComposicionHcGrafico(Request request, Response response) {


    //OBTENER PERIODOS
    PeriodoMensual inicio = this.getPeriodoInicio(request);
    PeriodoMensual fin = this.getPeriodoFin(request);

    //VALIDAR PERIODOS
    if(inicio.esDespuesDe(fin.getFecha())) {
      response.redirect("/home/composicion-hc");
      return null;
    }

    AgenteTerritorial agente = request.session().attribute("agente");
    //OBTENER SECTOR
    SectorTerritorial sector = agente.getSectorTerritorial();
    List<ComposicionHcSectorTerritorial> items = genReporte.reporteComposicionHC(inicio, fin, sector);

    Map<String,Object> model = new HashMap<>();
    model.put("items",items);
    model.put("sector",sector);
    model.put("inicio",inicio);
    model.put("fin",fin);

    return new ModelAndView(model,"agenteComposicionHCRespuesta.hbs");

  }

  public ModelAndView getEvolucionHc(Request request, Response response) {
    AgenteCuenta agenteCuenta = request.session().attribute("cuenta");

    return new ModelAndView(agenteCuenta,"agenteEvolucionHcConsulta.hbs");
  }

  public ModelAndView getEvolucionHcGrafico(Request request, Response response) {
    AgenteCuenta agenteCuenta = request.session().attribute("cuenta");

    PeriodoMensual inicio = getPeriodoInicio(request);
    PeriodoMensual fin = getPeriodoFin(request);

    AgenteTerritorial agente = request.session().attribute("agente");
    SectorTerritorial sector = agente.getSectorTerritorial();
    List<EvolucionHCSectorTerritorial> evols = genReporte.reporteEvolucionHC(sector,inicio,fin);

    Map<String,Object> model = new HashMap<>();
    model.put("items",evols);
    model.put("sector",sector);
    model.put("inicio",inicio);
    model.put("fin",fin);
    model.put("usuario",agenteCuenta.getUsuario());
    return new ModelAndView(model,"agenteEvolucionHcRespuesta.hbs");

}

  public ModelAndView getHcTotal(Request request,Response response) {

    AgenteCuenta cuenta = request.session().attribute("cuenta");
    AgenteTerritorial agente = request.session().attribute("agente");
    SectorTerritorial sector = agente.getSectorTerritorial();

    PeriodoMensual periodo = new PeriodoMensual(LocalDate.now());
    PeriodoAnual periodoAnual = new PeriodoAnual(LocalDate.now());
    Map<String,Object> model = new HashMap<>();

    //
    model.put("totalMensual",sector.calcularHC(periodo));
    //
    model.put("totalAnual",sector.calcularHC(periodoAnual));

    model.put("unidad", "ESTO NI IDEA");
    model.put("sector",sector);
    model.put("usuario",cuenta.getUsuario());
    model.put("periodoActual",periodo);

    return new ModelAndView(model,"agenteHome.hbs");
  }

  public ModelAndView getOrganizaciones(Request request,Response response) {

    AgenteCuenta cuenta = request.session().attribute("cuenta");
    AgenteTerritorial agente = request.session().attribute("agente");
    SectorTerritorial sector = agente.getSectorTerritorial();
    List<Organizacion> organizaciones = sector.getOrganizaciones();
    Map<String,Object> model = new HashMap<>();
    model.put("usuario",cuenta.getUsuario());
    model.put("organizaciones",organizaciones);

    return new ModelAndView(model,"agenteOrganizacion.hbs");
  }

  public ModelAndView getHcTipoOrg(Request request, Response response) {

    AgenteTerritorial agente = request.session().attribute("agente");
    AgenteCuenta cuenta = request.session().attribute("cuenta");
    PeriodoMensual periodo = new PeriodoMensual(LocalDate.now());
    PeriodoMensual periodo2 = new PeriodoMensual(LocalDate.now().plusDays(1L));
    HCPorTipoOrganizacion hcPorTipoOrganizacion = new ReporteOrganizaciones(RepoOrganizacion.getInstance())
        .hcPorTipoOrganizacion(periodo,periodo2)
        .stream()
        .filter(rep -> rep.getUnTipo() == TipoOrganizacion.valueOf(request.params("tipo"))).collect(Collectors.toList()).get(0);

    Map<String,Object> model = new HashMap<>();
    model.put("valorMensual",hcPorTipoOrganizacion.getHc());
    model.put("valorAnual",1000D);
    model.put("unidad", Unidad.LTS);
    model.put("sector",agente.getSectorTerritorial().getNombre());
    model.put("usuario",cuenta.getUsuario());
    model.put("periodoActual",periodo);
    model.put("tipo",hcPorTipoOrganizacion.getUnTipo());

    return new ModelAndView(model,"agenteHcTotalTipoOrg.hbs");
  }

  public ModelAndView getEvolucionHcOrg(Request request, Response response) {

    AgenteCuenta cuenta = request.session().attribute("cuenta");
    Long idOrg = Long.valueOf(request.params("id"));
    Organizacion org = RepoOrganizacion.getInstance().getOrganizacionById(idOrg);
    Map<String,Object> model = new HashMap<>();
    model.put("usuario",cuenta.getUsuario());
    model.put("nombreOrg",org.getRazonSocial());
    model.put("id",request.params("id"));

    return new ModelAndView(model,"agenteOrgEvolucionHCConsulta.hbs");
  }

  public ModelAndView getEvolucionHcOrgGrafico(Request request, Response response) {

    //OBTENER PERIODOS
    PeriodoMensual inicio = this.getPeriodoInicio(request);
    PeriodoMensual fin = this.getPeriodoFin(request);

    AgenteCuenta cuenta = request.session().attribute("cuenta");
    Long idOrg = Long.valueOf(request.params("id"));
    Organizacion org = RepoOrganizacion.getInstance().getOrganizacionById(idOrg);

    //VALIDAR PERIODOS
    if(inicio.esDespuesDe(fin.getFecha())) {
      response.redirect("/home/organizaciones");
      return null;
    }

    List<EvolucionHCOrganizacion> items = new ReporteOrganizaciones(RepoOrganizacion.getInstance()).evolucionHCEntre(org,inicio,fin);

    Map<String,Object> model = new HashMap<>();
    model.put("usuario",cuenta.getUsuario());
    model.put("nombreOrg",org.getRazonSocial());
    model.put("id",org.getId());
    model.put("items",items);

    return new ModelAndView(model,"agenteOrgEvolucionHCRespuesta.hbs");

  }

  public ModelAndView getComposicionHcOrg(Request request, Response response) {

    AgenteCuenta cuenta = request.session().attribute("cuenta");
    Long idOrg = Long.valueOf(request.params("id"));
    Organizacion org = RepoOrganizacion.getInstance().getOrganizacionById(idOrg);

    Map<String,Object> model = new HashMap<>();
    model.put("usuario",cuenta.getUsuario());
    model.put("nombreOrg",org.getRazonSocial());
    model.put("id",org.getId());

    return new ModelAndView(model,"agenteOrgComposicionHCConsulta.hbs");
  }

  public ModelAndView getComposicionHcOrgGrafico(Request request, Response response) {

    PeriodoMensual inicio = this.getPeriodoInicio(request);
    PeriodoMensual fin = this.getPeriodoFin(request);

    Long idOrg = Long.valueOf(request.params("id"));
    Organizacion org = RepoOrganizacion.getInstance().getOrganizacionById(idOrg);

    List<ComposicionHCOrganizacion> items = new ReporteOrganizaciones(RepoOrganizacion.getInstance())
        .composicionHCEntre(org,inicio, fin);

    Map<String,Object> model = new HashMap<>();
    model.put("nombreOrg",org.getRazonSocial());
    model.put("items",items);
    model.put("inicio",inicio);
    model.put("fin",fin);

    return new ModelAndView(model,"agenteOrgComposicionHCRespuesta.hbs");

  }


}