package exceptions;

public class ElPeriodoIngresadoNoEsValido extends RuntimeException {
  public ElPeriodoIngresadoNoEsValido(String periodoIngresado) {
    super("ingresado: " + periodoIngresado);
  }
}
