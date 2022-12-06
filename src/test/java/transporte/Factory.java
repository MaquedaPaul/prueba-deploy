package transporte;

import linea.PuntoUbicacion;

public class Factory {

  PuntoUbicacion libertador = crearPuntoUbicacion(1, "libertador", 0);
  PuntoUbicacion saenz = crearPuntoUbicacion(2, "saenz", 100);
  PuntoUbicacion boca = crearPuntoUbicacion(3, "boca", 200);
  PuntoUbicacion sanMartin = crearPuntoUbicacion(4, "sanMartin", 300);


  public PuntoUbicacion crearPuntoUbicacion(int localidadID, String unaCalle, int altura) {
    return new PuntoUbicacion(localidadID, unaCalle, altura);
  }

  public Tramo crearTramoSimple(Transporte unTransporte) {
    return new Tramo(boca, libertador, unTransporte);
  }

}


