package reporte.itemsreportes;

import lombok.Getter;
import organizacion.Organizacion;
import organizacion.periodo.PeriodoMensual;

import java.time.LocalDate;

@Getter
public class EvolucionHCOrganizacion {

  private final Organizacion organizacion;
  private final PeriodoMensual fecha;
  private final double valor;

  public EvolucionHCOrganizacion(Organizacion organizacion, PeriodoMensual fecha, double valor) {
    this.organizacion = organizacion;
    this.fecha = fecha;
    this.valor = valor;
  }
}
