package organizacion.periodo;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class PeriodoMensual implements Periodo {

  private final LocalDate fecha;
  private final int anio;
  private final int mes;

  public PeriodoMensual(LocalDate fecha) {
    this.fecha = fecha;
    anio = fecha.getYear();
    mes = fecha.getMonthValue();
  }

  public int getYear() {
    return fecha.getYear();
  }

  public int getMonth() {
    return fecha.getMonthValue();
  }

  public boolean esDelAnio(int year) {
    return this.getYear() == year;
  }

  public int perioricidad() {
    return 1;
  }

  public boolean esAntesDe(LocalDate fecha) {
    return this.getFecha().isBefore(fecha) || this.getFecha().isEqual(fecha);
  }

  public boolean esDespuesDe(LocalDate fecha) {
    return this.getFecha().isAfter(fecha) || this.getFecha().isEqual(fecha);
  }

  public boolean esDelMismoAnioQue(PeriodoMensual mensual) {
    return this.getYear() == mensual.getYear();
  }

  public int mesesDeDiferenciaCon(PeriodoMensual mensual) {
    int aniosDiferencia = Math.abs(mensual.getYear() - this.getYear());
    int mesesDiferencia = Math.abs(mensual.getMonth() - this.getMonth() + 1);

    return Math.abs(aniosDiferencia * 12 - mesesDiferencia);
  }

}
