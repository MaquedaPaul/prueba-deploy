package exceptions;

public class LaPerioricidadLeidaNoEsValida extends RuntimeException {
  public LaPerioricidadLeidaNoEsValida(long lineaConflictiva) {
    super("La periodicidad leida en la linea " + lineaConflictiva + " no es valida");
  }
}
