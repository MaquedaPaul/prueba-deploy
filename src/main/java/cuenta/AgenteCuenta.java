package cuenta;

import organizacion.periodo.PeriodoAnual;
import organizacion.periodo.PeriodoMensual;
import repositorios.RepoCuentas;
import spark.Request;
import territorio.AgenteTerritorial;
import territorio.SectorTerritorial;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Entity
public class AgenteCuenta extends Cuenta {

  public AgenteCuenta() {
  }

  public AgenteCuenta(String usuario, String password) {
    super(usuario, password);
  }

  public String home() {
    return "agenteHome.hbs";
  }

  public void guardarEnSesion(Request request) {
    request.session().attribute("cuenta",this);
    request.session().attribute("agente",RepoCuentas.getInstance().obtenerAgente(this));
  }

  @Override
  public boolean puedeAccederA(String path) {
    return TipoCuenta.AGENTE.puedeAccederA(path);
  }

  public Map<String, Object> datosDelHome(Request request) {
    Map<String, Object> model = new HashMap<>();
    AgenteTerritorial agente = request.session().attribute("agente");
    SectorTerritorial sectorTerritorial = agente.getSectorTerritorial();
    double totalAnual = sectorTerritorial.calcularHC(new PeriodoAnual(LocalDate.now()));
    double totalMensual = sectorTerritorial.calcularHC(new PeriodoMensual(LocalDate.now()));

    model.put("sector",sectorTerritorial);
    model.put("cuenta",this);
    model.put("periodoActual",new PeriodoMensual(LocalDate.now()));
    model.put("totalMensual",totalMensual);
    model.put("totalAnual",totalAnual);
    model.put("unidad","NIIDEAAAAA");
    return model;
  }

  @Override
  public void limpiarSession(Request request) {
    request.session().attribute("cuenta",null);
    request.session().attribute("agente",null);
  }

  @Override
  public String getRecomendaciones() {
    return null;
  }
}
