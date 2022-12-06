package organizacion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import organizacion.periodo.GeneradorDePeriodos;
import organizacion.periodo.PeriodoMensual;

import java.time.LocalDate;
import java.util.List;

public class GeneradorDePeriodosTest {

  @Test
  public void entreDIC2020yAGO2022Hay22PeriodosMensuales() {

    GeneradorDePeriodos gen = new GeneradorDePeriodos();
    PeriodoMensual dic2020 = new PeriodoMensual(LocalDate.of(2020, 12, 1));
    PeriodoMensual ago2022 = new PeriodoMensual(LocalDate.of(2022, 8, 1));
    //12,1,2,3,4,5,6,7,8,9,10,11,12,1,2,3,4,5,6,7,8
    List<PeriodoMensual> periodos = gen.generarPeriodosMensualesEntre(dic2020, ago2022);

    Assertions.assertEquals(21, periodos.size());
    Assertions.assertEquals(LocalDate.of(2022, 8, 1), periodos.get(20).getFecha());
  }
}
