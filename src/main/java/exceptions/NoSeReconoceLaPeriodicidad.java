package exceptions;

public class NoSeReconoceLaPeriodicidad extends RuntimeException {
  public NoSeReconoceLaPeriodicidad(int lineaConflictiva) {
    super("No se reconoce la periodicidad de la linea" + lineaConflictiva);
  }
}
