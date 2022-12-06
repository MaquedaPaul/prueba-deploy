package services.geodds.entities;

import global.Unidad;

public class Distancia {
  public double valor;
  public Unidad unidad;

  public Distancia(double valor) {
    this.valor = valor;
  }

  public Unidad getUnidad() {
    return unidad;
  }

  public double getValor() {
    return valor;
  }

  public void setUnidad(Unidad unidad) {
    this.unidad = unidad;
  }

  public void setValor(double valor) {
    this.valor = valor;
  }


}
