package transporte;

import lombok.Getter;
import tipoconsumo.TipoConsumo;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

@Getter
@Entity
@Table(name = "TRAYECTO")
public class Trayecto {
  @Id
  @GeneratedValue
  @Column(name = "ID_TRAYECTO")
  Long id;

  @ManyToMany(cascade = CascadeType.PERSIST)
  @JoinTable(name = "TRAMO_POR_TRAYECTO",
      joinColumns = @JoinColumn(name = "ID_TRAYECTO"),
      inverseJoinColumns = @JoinColumn(name = "ID_TRAMO"))
  private Set<Tramo> tramos = new HashSet<>();

  public Trayecto() {
  }

  public Trayecto(Set<Tramo> tramos) {
    this.tramos = tramos;
  }

  public void setTramos(Set<Tramo> tramos) {
    this.tramos = tramos;
  }

  public void agregarTramo(Tramo unTramo) {
    this.tramos.add(unTramo);
  }

  public boolean sePuedeCompartir() {
    return tramos.stream().allMatch(Tramo::sePuedeCompartir);
  }

  /*
   * public void setOrigen(String origen) { this.origen = origen; }
   */

  /* //TODO No Compila
  public PuntoUbicacion getOrigen() {
    return tramos.get(0).getPuntoOrigen();
  }

  public PuntoUbicacion getDestino() {
    return tramos.get(tramos.size() - 1).getPuntoDestino();
  }
  */
  /*
   * public void setDestino(String destino) { this.destino = destino; }
   */

  public double distanciaTotal() {
    return tramos.stream().mapToDouble(Tramo::distanciaTramo).sum();
  }

  public double calcularHC() {
    return tramos.stream().mapToDouble(Tramo::calcularHc).sum();
  }

  public Stream<TipoConsumo> getTiposDeConsumo() {
    return getTramos().stream().map(Tramo::getTipoConsumo);
  }

  public Set<Tramo> getTramos() {
    return tramos;
  }
}