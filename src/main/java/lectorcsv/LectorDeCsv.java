package lectorcsv;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import exceptions.*;
import lombok.Getter;
import lombok.Setter;
import mediciones.Medicion;
import repositorios.RepoMediciones;
import organizacion.Organizacion;
import tipoconsumo.TipoConsumo;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Getter
public class LectorDeCsv {

  private final CSVReader reader;
  private final Organizacion organizacion;
  private final FormatoDeFechas formatoDeFechas;
  private final ValidadorDeCabeceras cabeceraEsperada;

  @Setter
  private RepoTipoConsumo repoConsumos;
  private TipoConsumo tipoConsumo;
  private double valor;
  private LocalDate fecha;
  private TipoPerioricidad perioricidad;

  private final List<Medicion> mediciones = new ArrayList<>();


  public LectorDeCsv(String path, Organizacion organizacion, FormatoDeFechas formato, ValidadorDeCabeceras cabeceraEsperada, RepoTipoConsumo repo) throws IOException {
    this.reader = new CSVReader(new InputStreamReader(Files.newInputStream(Paths.get(path)),
        Charset.defaultCharset()));
    this.organizacion = organizacion;
    this.formatoDeFechas = formato;
    this.cabeceraEsperada = cabeceraEsperada;
    this.repoConsumos = repo;
  }

  public int getCantidadDeMediciones() {
    return mediciones.size();
  }

  public void leerMediciones() {
    List<String> linea = this.lineaLeida();
    if (!this.esUnaCabeceraValida(linea)) {
      throw new LaCabeceraNoTieneUnFormatoValido();
    }
    linea = this.lineaLeida();
    while ((!linea.isEmpty()) && !Objects.equals(linea.get(0), "")) {
      this.validarFormatoLeido(linea);
      this.asignarParametros(linea);
      this.guardarMedicion();
      linea = this.lineaLeida();
    }
  }

  private boolean esUnaCabeceraValida(List<String> cabecera) {
    return cabeceraEsperada.validar(cabecera);
  }

  private List<String> lineaLeida() {
    String[] linea;
    try {
      linea = reader.readNext();
    } catch (IOException | CsvValidationException e) {
      e.printStackTrace();
      throw new NoSePudoLeerLaLinea(this.lineaActual());
    }
    if (linea == null) {
      return new ArrayList<>();
    }
    return Arrays.asList(linea);

  }

  private long lineaActual() {
    return reader.getLinesRead();
  }

  public void validarFormatoLeido(List<String> campos) {

    perioricidad = TipoPerioricidad.valueOf(campos.get(2));

    this.tieneLaCantidadCorrectaDeColumnas(campos);
    this.esUnTipoDeConsumoValido(campos.get(0));
    this.elValorLeidoEsPositivo(Integer.parseInt(campos.get(1)));
    this.esUnaPerioricidadValida(campos.get(2));
    this.tieneElFormatoValido(campos.get(3), perioricidad);

  }

  public void tieneLaCantidadCorrectaDeColumnas(List<String> columnas) {
    if (columnas.size() != cabeceraEsperada.getCantidadDeColumnas()) {
      throw new NoSeLeyeronLosCamposEsperados(cabeceraEsperada.getCantidadDeColumnas(), columnas.size(), this.lineaActual());
    }
  }

  public void esUnTipoDeConsumoValido(String tipoConsumo) {
    if (!repoConsumos.existeElTipoDeConsumo(tipoConsumo)) {
      throw new ElTipoDeConsumoLeidoNoEsValido(this.lineaActual());
    }
  }

  public void elValorLeidoEsPositivo(int valor) {
    if (valor <= 0) {
      throw new LaMedicionEsNegativa(this.lineaActual());
    }
  }

  public void esUnaPerioricidadValida(String perioricidad) {
    try {
      TipoPerioricidad.valueOf(perioricidad);
    } catch (IllegalArgumentException e) {
      throw new LaPerioricidadLeidaNoEsValida(this.lineaActual());
    }
  }

  public void tieneElFormatoValido(String periodoDeImputacion, TipoPerioricidad perioricidad) {

    if (!formatoDeFechas.tieneElFormatoValido(periodoDeImputacion, perioricidad)) {
      throw new ElPeriodoNoConcuerdaConLaPerioricidad(this.lineaActual());
    }
  }

  private void asignarParametros(List<String> atributos) {

    fecha = this.getFormatoDeFechas().parsear(atributos.get(3),
        TipoPerioricidad.valueOf(atributos.get(2)));

    this.tipoConsumo = repoConsumos.getTipoConsumo(atributos.get(0));
    this.valor = Integer.parseInt(atributos.get(1));

  }

  private void guardarMedicion() {
    Medicion medicion = perioricidad.buildMedicion(organizacion,tipoConsumo,fecha,valor);
    mediciones.add(medicion);
  }

  public void cargarMediciones() {
    mediciones.forEach(medicion -> RepoMediciones.getInstance().cargarMedicion(medicion));
  }

}
