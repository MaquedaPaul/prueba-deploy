package exceptions;

public class ElPeriodoNoConcuerdaConLaPerioricidad extends RuntimeException {
  public ElPeriodoNoConcuerdaConLaPerioricidad(long lineaConflictiva) {
    super("El periodo no coincide con la perioricidad en la linea: " + lineaConflictiva);
  }
}
