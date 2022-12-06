package lectorcsv;

import organizacion.Organizacion;
import repositorios.RepoTipoDeConsumo;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LectorMediciones {

  private final LectorDeCsv lector;

  public LectorMediciones(String path, Organizacion organizacion) {

    DateTimeFormatter formatoMensual = new DateTimeFormatterBuilder()
        .appendPattern("MM/yyyy")
        .parseDefaulting(ChronoField.DAY_OF_MONTH, 15)
        .toFormatter();
    DateTimeFormatter formatoAnual = new DateTimeFormatterBuilder()
        .appendPattern("yyyy")
        .parseDefaulting(ChronoField.DAY_OF_MONTH, 15)
        .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
        .toFormatter();

    HashMap<TipoPerioricidad, DateTimeFormatter> formatos = new HashMap<>();
    formatos.put(TipoPerioricidad.ANUAL, formatoAnual);
    formatos.put(TipoPerioricidad.MENSUAL, formatoMensual);

    List<String> columnasEsperadas = new ArrayList<>();
    columnasEsperadas.add("tipoconsumo");
    columnasEsperadas.add("valor");
    columnasEsperadas.add("perioricidad");
    columnasEsperadas.add("periodo de imputacion");

    try {
      lector = new LectorDeCsv(
          path,
          organizacion,
          new FormatoDeFechas(formatos),
          new ValidadorDeCabeceras(columnasEsperadas),
          RepoTipoDeConsumo.getInstance());
    } catch (Exception e) {
      throw new RuntimeException("NO SE PUDO CREAR EL LECTOR, REVISE EL PATH");
    }
  }

  public void leerMediciones() {
    lector.leerMediciones();
  }

  public void cargarMediciones() {
    lector.cargarMediciones();
  }
}
