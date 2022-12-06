package reporte.itemsreportes;

import lombok.Getter;
import organizacion.periodo.PeriodoMensual;
import territorio.SectorTerritorial;

import java.time.LocalDate;

@Getter
public class EvolucionHCSectorTerritorial {

  private final SectorTerritorial sector;
  private final PeriodoMensual periodo;
  private final double valor;

  public EvolucionHCSectorTerritorial(SectorTerritorial sector, PeriodoMensual fecha, double valor) {
    this.sector = sector;
    this.periodo = fecha;
    this.valor = valor;
  }
}
