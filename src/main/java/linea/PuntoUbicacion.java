package linea;

import javax.persistence.*;

@Entity
public class PuntoUbicacion {

  @Id
  @GeneratedValue
  @Column(name = "ID_PUNTO_UBICACION")
  Long id;

  @Column(name = "ID_LOCALIDAD")
  private int localidadId;

  @Column(name = "CALLE")
  private String calle;

  @Column(name = "ALTURA")
  private int altura;

  public PuntoUbicacion() {
  }

  public PuntoUbicacion(int localidadId, String calle, int altura) {
    this.localidadId = localidadId;
    this.calle = calle;
    this.altura = altura;
  }

  public int getLocalidadId() {
    return localidadId;
  }

  public String getCalle() {
    return calle;
  }

  public int getAltura() {
    return altura;
  }

  public boolean esIgualA(PuntoUbicacion puntoUbicacion) {
    return this.localidadId == puntoUbicacion.getLocalidadId() && this.calle.equals(puntoUbicacion.getCalle())  &&
        this.altura == puntoUbicacion.getAltura();
  }

    public boolean sonIguales(PuntoUbicacion puntoDestino) {
    //TODO
    return true;
    }
}
