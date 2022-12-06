package organizacion.periodo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GeneradorDePeriodos {

  public GeneradorDePeriodos() {

  }

  public List<PeriodoMensual> generarPeriodosMensualesEntre(PeriodoMensual inicio, PeriodoMensual fin) {
    List<PeriodoMensual> periodos = new ArrayList<>();
    LocalDate fechaInicio = inicio.getFecha();
    int cantidadDePeriodos = inicio.mesesDeDiferenciaCon(fin);
    for (int i = 0; i < cantidadDePeriodos; i++) {
      periodos.add(new PeriodoMensual(fechaInicio.plusMonths(i)));
    }
    return periodos;
  }
}
