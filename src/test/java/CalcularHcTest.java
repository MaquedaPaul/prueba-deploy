import admin.FactorEmision;
import global.Unidad;
import linea.LineaTransporte;
import linea.Parada;
import linea.TipoTransporte;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tipoconsumo.TipoActividad;
import tipoconsumo.TipoAlcance;
import tipoconsumo.TipoConsumo;
import transporte.Combustible;
import transporte.TipoVehiculo;
import transporte.TransportePublico;
import transporte.VehiculoParticular;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CalcularHcTest {

  TipoConsumo tipoConsumoEjemplo = new TipoConsumo("text", Unidad.LTS, TipoActividad.COMBUSTION_FIJA, TipoAlcance.EMISION_DIRECTA);

  @BeforeEach
  public void inicializarTipoConsumo() {
    tipoConsumoEjemplo.setFactorEmision(new FactorEmision(20, Unidad.LTS));
  }

  @Test
  public void unTransportePrivadoPuedeCalcularSuHuellaDeCarbono() {
    VehiculoParticular unVehiculo = new VehiculoParticular(TipoVehiculo.AUTO, 10,"Peugeot");
    Combustible unCombustible = new Combustible(tipoConsumoEjemplo);
    unVehiculo.setCombustible(unCombustible);
    assertEquals(unVehiculo.calcularHc(), 200);
  }

  @Test
  public void unTransportePublicoPuedeCalcularSuHuellaDeCarbono() {
    // LISTA DE Ubicaciones
    List<Parada> paradasDeIdaDel138 = new ArrayList<>();
    List<Parada> paradasDeVueltaDel138 = new ArrayList<>();
    // LINEA TRANSPORTE
    LineaTransporte linea138 =
        new LineaTransporte(TipoTransporte.COLECTIVO, "linea138", paradasDeIdaDel138, paradasDeVueltaDel138);
    TransportePublico unColectivo = new TransportePublico(linea138, 20);
    Combustible unCombustible = new Combustible(tipoConsumoEjemplo);
    unColectivo.setCombustible(unCombustible);
    FactorEmision unFactor = new FactorEmision(5, Unidad.LTS);
    assertEquals(unColectivo.calcularHc(), 400);
  }
}
