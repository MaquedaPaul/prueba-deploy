package exceptions;

public class NoSePudoAbrirElArchivo extends RuntimeException {
  public NoSePudoAbrirElArchivo(String path) {
    super("No se pudo abrir el archivo en la direccion " + path);
  }
}
