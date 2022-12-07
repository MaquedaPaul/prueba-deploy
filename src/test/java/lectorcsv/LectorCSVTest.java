package lectorcsv;

import exceptions.*;
import global.Unidad;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import organizacion.Organizacion;
import organizacion.TipoOrganizacion;
import repositorios.RepoOrganizacion;
import repositorios.RepoTipoDeConsumo;
import tipoconsumo.TipoActividad;
import tipoconsumo.TipoAlcance;
import tipoconsumo.TipoConsumo;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LectorCSVTest implements WithGlobalEntityManager {

  static Organizacion mockOrg;
  static FormatoDeFechas formato;
  static ValidadorDeCabeceras validador;
  static LectorDeCsv lector;

  List<String> campos = mock(ArrayList.class);

  static TipoConsumo gas = new TipoConsumo("Gas Natural",
      Unidad.CM3, TipoActividad.COMBUSTION_MOVIL,
      TipoAlcance.EMISION_DIRECTA);
  static TipoConsumo nafta = new TipoConsumo("Nafta",
      Unidad.LTS, TipoActividad.COMBUSTION_MOVIL,
      TipoAlcance.EMISION_DIRECTA);

  static DateTimeFormatter formatoMensual = new DateTimeFormatterBuilder()
      .appendPattern("MM/yyyy")
      .parseDefaulting(ChronoField.DAY_OF_MONTH, 15)
      .toFormatter();
  static DateTimeFormatter formatoAnual = new DateTimeFormatterBuilder()
      .appendPattern("yyyy")
      .parseDefaulting(ChronoField.DAY_OF_MONTH, 15)
      .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
      .toFormatter();

  @BeforeAll
  static void inicializar() {
    mockOrg = new Organizacion("", TipoOrganizacion.EMPRESA,"","",new ArrayList<>());

    HashMap<TipoPerioricidad, DateTimeFormatter> formatos = new HashMap<>();
    formatos.put(TipoPerioricidad.ANUAL, formatoAnual);
    formatos.put(TipoPerioricidad.MENSUAL, formatoMensual);
    formato = new FormatoDeFechas(formatos);

    List<String> columnasEsperadas = new ArrayList<>();
    columnasEsperadas.add("tipoconsumo");
    columnasEsperadas.add("valor");
    columnasEsperadas.add("perioricidad");
    columnasEsperadas.add("periodo de imputacion");

    validador = new ValidadorDeCabeceras(columnasEsperadas);
    try {
      lector = new LectorDeCsv("src/test/resources/mediciones-test.csv", mockOrg, formato, validador, RepoTipoDeConsumo.getInstance());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    RepoTipoDeConsumo.getInstance().agregarNuevoTipoDeConsumo(gas);
    RepoTipoDeConsumo.getInstance().agregarNuevoTipoDeConsumo(nafta);
    RepoOrganizacion.getInstance().agregarOrganizacion(mockOrg);
  }

  @Test
  public void elLectorFallaSiLaRutaDelArchivoNoExiste() {
    assertThrows(NoSuchFileException.class,
        () -> new LectorDeCsv("hola no funciono", mockOrg, formato, validador, RepoTipoDeConsumo.getInstance()));
  }

  @Test
  public void siLeiMasColumnasDeLasEsperadasDeboFallar() {
    when(campos.size()).thenReturn(3);
    assertThrows(NoSeLeyeronLosCamposEsperados.class,
        () -> lector.tieneLaCantidadCorrectaDeColumnas(campos));
  }

  @Test
  public void siLeoUnTipoDeConsumoDesconocidoTengoQueDetenerme() {
    assertThrows(ElTipoDeConsumoLeidoNoEsValido.class, () -> lector.esUnTipoDeConsumoValido("NAFFFFFFFTAAAAA"));
  }

  @Test
  public void siLeoUnValorNegativoTengoQueDetenerme() {
    assertThrows(LaMedicionEsNegativa.class, () -> lector.elValorLeidoEsPositivo(-1));
  }

  @Test
  public void siLeoUnaPerioricidadInvalidoTengoQueDetenerme() {
    assertThrows(LaPerioricidadLeidaNoEsValida.class,
        () -> lector.esUnaPerioricidadValida("MENNNNNSUAL"));
  }

  @Test
  public void siLeoUnPeriodoDeImputacionInvalidoTengoQueDetenerme() {
    assertThrows(ElPeriodoNoConcuerdaConLaPerioricidad.class,
        () -> lector.tieneElFormatoValido("11/2000", TipoPerioricidad.ANUAL));
  }

  @Test
  public void puedoGuardarLasMedicionesQueEstenCorrectas() {
    Assertions.assertEquals(lector.getCantidadDeMediciones(), 0);
    assertDoesNotThrow(lector::leerMediciones);
    Assertions.assertEquals(10, lector.getCantidadDeMediciones());
  }

  @Test
  public void lectorMedicionesAnda() {
    LectorMediciones lector = new LectorMediciones("src/test/resources/mediciones-test.csv",mockOrg);
    assertDoesNotThrow(lector::leerMediciones);
    assertDoesNotThrow(lector::cargarMediciones);
  }
}
