package lectorcsv;

import java.util.List;

public class ValidadorDeCabeceras {

  private final List<String> cabecera;

  public ValidadorDeCabeceras(List<String> nombresColumnas) {
    this.cabecera = nombresColumnas;
  }

  public boolean validar(List<String> cabecera) {
    return cabecera.equals(this.cabecera);
  }

  public int getCantidadDeColumnas() {
    return cabecera.size();
  }

}
