package exceptions;

public class NoSePudoLeerLaLinea extends RuntimeException {
  public NoSePudoLeerLaLinea(long lineaConflictiva) {
    super("No se pudo leer la linea nro " + lineaConflictiva + " del archivo");
  }
}
