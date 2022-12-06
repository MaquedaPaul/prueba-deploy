package exceptions;

public class LaMedicionEsNegativa extends RuntimeException {
  public LaMedicionEsNegativa(long lineaConflictiva) {
    super("La medicion es negativa en la linea " + lineaConflictiva);
  }
}
