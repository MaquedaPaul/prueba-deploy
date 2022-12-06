package territorio;

import lombok.Getter;
import organizacion.periodo.PeriodoMensual;

@Getter
public class HCPorSectorTerritorial {

  private final double hcTotal;
  private final SectorTerritorial sectorTerritorial;
  private final PeriodoMensual periodoMensual;

  public HCPorSectorTerritorial(double hc, SectorTerritorial sector, PeriodoMensual periodoMensual) {
    this.hcTotal = hc;
    this.sectorTerritorial = sector;
    this.periodoMensual = periodoMensual;
  }

}
