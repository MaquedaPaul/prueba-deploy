package exceptions;

public class ElTipoDeConsumoLeidoNoEsValido extends RuntimeException {
  public ElTipoDeConsumoLeidoNoEsValido(long lineaConflictiva) {
    super("El tipo de consumo leido en la linea " + lineaConflictiva + "no existe");
  }
}
