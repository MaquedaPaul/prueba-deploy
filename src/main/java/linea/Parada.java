package linea;

import javax.persistence.*;

@Entity
public class Parada {

  @Id
  @GeneratedValue
  @Column(name = "ID_PARADA")
  Long id;

  @Column(name = "KM_ACTUAL")
  private int kmActual;

  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "ID_PUNTO_UBICACION")
  private PuntoUbicacion puntoUbicacion;

  public Parada() {
  }

  public Parada(int kmActual,PuntoUbicacion puntoUbicacion) {
    this.kmActual = kmActual;
    this.puntoUbicacion = puntoUbicacion;
  }

  public int getKmActual() {
    return kmActual;
  }

  public PuntoUbicacion getPuntoUbicacion(){
    return this.puntoUbicacion;
  }

}