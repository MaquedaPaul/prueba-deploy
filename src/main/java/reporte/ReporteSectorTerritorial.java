package reporte;

import organizacion.periodo.GeneradorDePeriodos;
import organizacion.periodo.PeriodoMensual;
import reporte.itemsreportes.ComposicionHcSectorTerritorial;
import reporte.itemsreportes.EvolucionHCSectorTerritorial;
import territorio.HCPorSectorTerritorial;
import territorio.SectorTerritorial;

import java.util.List;
import java.util.stream.Collectors;

public class ReporteSectorTerritorial {

  private final GeneradorDePeriodos gen = new GeneradorDePeriodos();

  public List<ComposicionHcSectorTerritorial> reporteComposicionHC(PeriodoMensual inicio, PeriodoMensual fin, SectorTerritorial sectorTerritorial) {

    List<PeriodoMensual> periodos = gen.generarPeriodosMensualesEntre(inicio, fin);

    return periodos.stream()
        .map(periodo -> this.generarItemComposicionHCEn(sectorTerritorial, periodo))
        .collect(Collectors.toList());
  }

  public ComposicionHcSectorTerritorial generarItemComposicionHCEn(SectorTerritorial sector, PeriodoMensual periodo) {
    return new ComposicionHcSectorTerritorial(sector,
        sector.calcularHCMiembros(periodo),
        sector.calcularHCMediciones(periodo),
        periodo);
  }

  public List<HCPorSectorTerritorial> hcPorSectorTerritorial(PeriodoMensual inicio, PeriodoMensual fin, SectorTerritorial sectorTerritorial) {
    //TODO que es esto
    //List<PeriodoMensual> periodos = gen.generarPeriodosMensualesEntre(inicio, fin);

    return null;

  }

  private HCPorSectorTerritorial generarItemHCPorSectorTerritorial(SectorTerritorial sector, PeriodoMensual periodo) {
    return new HCPorSectorTerritorial(sector.calcularHC(periodo), sector, periodo);
  }

  public List<EvolucionHCSectorTerritorial> reporteEvolucionHC(SectorTerritorial sector, PeriodoMensual inicio, PeriodoMensual fin) {

    GeneradorDePeriodos gen = new GeneradorDePeriodos();
    List<PeriodoMensual> periodos = gen.generarPeriodosMensualesEntre(inicio, fin);

    return periodos
        .stream()
        .map(periodo -> this.crearItemDelReporteDeEvolucionHC(sector, periodo))
        .collect(Collectors.toList());
  }

  private EvolucionHCSectorTerritorial crearItemDelReporteDeEvolucionHC(SectorTerritorial sector, PeriodoMensual periodo) {
    return new EvolucionHCSectorTerritorial(sector, periodo, sector.calcularHC(periodo));
  }
}
