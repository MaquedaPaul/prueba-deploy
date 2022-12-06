package reporte.itemsreportes;

import lombok.Getter;
import organizacion.periodo.PeriodoMensual;
import territorio.SectorTerritorial;

@Getter
public class ComposicionHcSectorTerritorial {

  private final SectorTerritorial sectorTerritorial;
  private final double hcMiembros;
  private final double hcMediciones;
  private final PeriodoMensual periodoMensual;


  public ComposicionHcSectorTerritorial(SectorTerritorial sectorTerritorial, double hcMiembros, double hcMediciones, PeriodoMensual periodoMensual) {
    this.sectorTerritorial = sectorTerritorial;
    this.hcMiembros = hcMiembros;
    this.hcMediciones = hcMediciones;
    this.periodoMensual = periodoMensual;
  }


}
