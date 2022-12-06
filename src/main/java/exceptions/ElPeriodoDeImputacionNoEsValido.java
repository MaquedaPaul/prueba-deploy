package exceptions;

public class ElPeriodoDeImputacionNoEsValido extends RuntimeException {
  public ElPeriodoDeImputacionNoEsValido(long lineaConflictiva) {
    super("El periodo de imputacion de la linea " + lineaConflictiva + "  no es valido");
  }
}
