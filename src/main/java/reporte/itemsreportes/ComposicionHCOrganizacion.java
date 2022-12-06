package reporte.itemsreportes;

import lombok.Getter;
import organizacion.Organizacion;
import organizacion.periodo.PeriodoMensual;

@Getter
public class ComposicionHCOrganizacion {

  private final Organizacion organizacion;
  private final double hcMiembros;
  private final double hcMediciones;
  private final PeriodoMensual periodo;


  public ComposicionHCOrganizacion(Organizacion organizacion, double hcMiembros, double hcMediciones, PeriodoMensual periodo) {
    this.organizacion = organizacion;
    this.hcMiembros = hcMiembros;
    this.hcMediciones = hcMediciones;
    this.periodo = periodo;
  }
}
