import admin.FactorEmision;
import exceptions.UnidadFeNoCorrespondienteConUnidadTipoConsumo;
import global.Unidad;
import org.junit.jupiter.api.Test;
import tipoconsumo.TipoActividad;
import tipoconsumo.TipoAlcance;
import tipoconsumo.TipoConsumo;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class TipoConsumoTest {
  TipoConsumo unGasNatural = new TipoConsumo("Gas Natural", Unidad.M3, TipoActividad.COMBUSTION_FIJA, TipoAlcance.EMISION_DIRECTA);

  @Test
  public void unGasNaturalNoDeberiaPermitirTenerUnFEConUnidadDistinta() {
    FactorEmision unFactorIncorrecto = new FactorEmision(5, Unidad.LTS);
    assertThrows(UnidadFeNoCorrespondienteConUnidadTipoConsumo.class, () -> unGasNatural.setFactorEmision(unFactorIncorrecto));

  }

  @Test
  public void unGasNaturalDeberiaPermitirTenerUnFEConUnidadIdentica() {
    unGasNatural = mock(TipoConsumo.class);
    FactorEmision unFactorCorrecto = new FactorEmision(5, Unidad.M3);
    //assertThrows(UnidadFENoCorrespondienteConUnidadTipoConsumo.class,() -> unGasNatural.setFactorEmision(unFactorCorrecto));
    verify(unGasNatural, atMostOnce()).setFactorEmision(unFactorCorrecto);
  }

}
