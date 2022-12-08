package transporte;

import linea.PuntoUbicacion;
import tipoconsumo.TipoConsumo;

import javax.persistence.*;
import java.io.IOException;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TRANSPORTE_UTILIZADO")
public abstract class Transporte {

  @Id
  @GeneratedValue
  @Column(name = "ID_TRANSPORTE")
  Long id;

  @Column(name = "CONSUMO_POR_KM")
  double consumoPorKilometro;

  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "ID_COMBUSTIBLE")
  Combustible combustible;

  public boolean sePuedeCompartir() {
    return false;
  }

  public abstract double distanciaEntre(PuntoUbicacion origen,
                                        PuntoUbicacion destino) throws IOException;

  public double calcularHc() {
    return combustible.obtenerValorEmision() * this.consumoPorKilometro;
  }

  public Combustible getCombustible() {
    return this.combustible;
  }

  public void setCombustible(Combustible combustible) {
    this.combustible = combustible;
  }

  public TipoConsumo getTipoConsumo() {
    return combustible.getTipoConsumo();
  }

  public abstract TipoTransporte getTipoTransporte();

  public abstract String getDisplay();
}


