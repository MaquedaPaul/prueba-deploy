package transporte;

import exceptions.NoSePudoCalcularElTramo;
import linea.PuntoUbicacion;
import lombok.Getter;
import tipoconsumo.TipoConsumo;

import javax.persistence.*;
import java.io.IOException;

@Getter
@Entity
public class Tramo {

  @Id
  @GeneratedValue
  @Column(name = "ID_TRAMO")
  Long id;

  @OneToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "ID_PUNTO_ORIGEN")
  private PuntoUbicacion puntoOrigen;

  @OneToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "ID_PUNTO_DESTINO")
  private PuntoUbicacion puntoDestino;

  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "ID_TRANSPORTE")
  private Transporte transporteUtilizado;

  public Tramo() {
  }

  public Tramo(PuntoUbicacion puntoOrigen,
               PuntoUbicacion puntoDestino,
               Transporte transporteUtilizado) {
    this.puntoOrigen = puntoOrigen;
    this.puntoDestino = puntoDestino;
    this.transporteUtilizado = transporteUtilizado;
  }

  public PuntoUbicacion getPuntoOrigen() {
    return puntoOrigen;
  }

  public PuntoUbicacion getPuntoDestino() {
    return puntoDestino;
  }

  public Transporte getTransporteUtilizado() {
    return transporteUtilizado;
  }

  public double distanciaTramo() {
    try {
      return this.transporteUtilizado.distanciaEntre(puntoOrigen, puntoDestino);
    } catch (IOException e) {
      e.printStackTrace();
    }
    throw new NoSePudoCalcularElTramo("API sin conexion");
  }

  public boolean sePuedeCompartir() {
    return this.getTransporteUtilizado().sePuedeCompartir();
  }

  public double calcularHc() {
    return transporteUtilizado.calcularHc() * distanciaTramo();
  }

  public TipoConsumo getTipoConsumo() {
    return this.transporteUtilizado.getTipoConsumo();
  }

  public void setPuntoDestino(PuntoUbicacion puntoDestino) {
    this.puntoDestino = puntoDestino;
  }

  public void setPuntoOrigen(PuntoUbicacion puntoOrigen) {
    this.puntoOrigen = puntoOrigen;
  }

  public void setTransporteUtilizado(Transporte transporteUtilizado) {
    this.transporteUtilizado = transporteUtilizado;
  }
}