package exceptions;

public class NoSeLeyeronLosCamposEsperados extends RuntimeException {
  public NoSeLeyeronLosCamposEsperados(int camposEsperados, int camposLeidos, long lineaActual) {
    super("Se leyeron mas campos de los esperados en la linea "
        + lineaActual
        + " , esperados: "
        + camposEsperados
        + ", leidos: "
        + camposLeidos);
  }
}
