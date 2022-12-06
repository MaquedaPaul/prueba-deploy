package organizacion.periodo;

import java.time.LocalDate;

public class PeriodoAnual implements Periodo {

  private final LocalDate fecha;

  public PeriodoAnual(LocalDate fecha) {
    this.fecha = fecha;
  }

  public int getYear() {
    return fecha.getYear();
  }

  public int getMonth() {
    return fecha.getMonthValue();
  }

  public int perioricidad() {
    return 12;
  }
}
