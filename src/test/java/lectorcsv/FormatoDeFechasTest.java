package lectorcsv;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.HashMap;

public class FormatoDeFechasTest {

  FormatoDeFechas formatoFechas;
  DateTimeFormatter formatoMensual = new DateTimeFormatterBuilder()
      .appendPattern("MM/yyyy")
      .parseDefaulting(ChronoField.DAY_OF_MONTH, 15)
      .toFormatter();
  DateTimeFormatter formatoAnual = new DateTimeFormatterBuilder()
      .appendPattern("yyyy")
      .parseDefaulting(ChronoField.DAY_OF_MONTH, 15)
      .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
      .toFormatter();

  HashMap<TipoPerioricidad, DateTimeFormatter> formato = new HashMap<>();


  @Test
  public void unaFechaMesYAnioNoEsValidaEnFormatoSoloAnual() {
    formato.put(TipoPerioricidad.ANUAL, formatoAnual);
    formatoFechas = new FormatoDeFechas(formato);
    Assertions.assertFalse(formatoFechas.tieneElFormatoValido("08/2011", TipoPerioricidad.ANUAL));
  }

  @Test
  public void mensualNoEsUnPeriodoValidoSiNoSeCargaEnElFormato() {
    formato.put(TipoPerioricidad.ANUAL, formatoAnual);

    formatoFechas = new FormatoDeFechas(formato);
    Assertions.assertFalse(formatoFechas.esUnPeriodoValido(TipoPerioricidad.MENSUAL));

  }

  @Test
  public void unaFechaMensualSoloEncajaConUnFormatoMensual() {
    formato.put(TipoPerioricidad.ANUAL, formatoAnual);
    formato.put(TipoPerioricidad.MENSUAL, formatoMensual);

    formatoFechas = new FormatoDeFechas(formato);
    Assertions.assertFalse(formatoFechas.tieneElFormatoValido("12/2009", TipoPerioricidad.ANUAL));
    Assertions.assertTrue(formatoFechas.tieneElFormatoValido("12/2009", TipoPerioricidad.MENSUAL));
  }

  @Test
  public void unMesMayorA12EsInvalido() {
    formato.put(TipoPerioricidad.ANUAL, formatoAnual);
    formato.put(TipoPerioricidad.MENSUAL, formatoMensual);

    formatoFechas = new FormatoDeFechas(formato);
    Assertions.assertFalse(formatoFechas.tieneElFormatoValido("22/2009", TipoPerioricidad.MENSUAL));
  }
}
