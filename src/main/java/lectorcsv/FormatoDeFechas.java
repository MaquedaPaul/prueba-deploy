package lectorcsv;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

public class FormatoDeFechas {

  private final HashMap<TipoPerioricidad, DateTimeFormatter> formatos;

  public FormatoDeFechas(HashMap<TipoPerioricidad, DateTimeFormatter> formatos) {
    this.formatos = formatos;
  }

  public boolean esUnPeriodoValido(TipoPerioricidad periodo) {
    return formatos.containsKey(periodo);
  }

  //TODO mejorar
  public boolean tieneElFormatoValido(String periodoImputacion, TipoPerioricidad perioricidad) {
    if (!formatos.containsKey(perioricidad)) {
      return false;
    }
    try {
      formatos.get(perioricidad).parse(periodoImputacion);
    } catch (DateTimeParseException ignored) {
      return false;
    }
    return true;
  }

  public LocalDate parsear(String fecha, TipoPerioricidad tipo) {
    return LocalDate.from(formatos.get(tipo).parse(fecha));
  }
}
