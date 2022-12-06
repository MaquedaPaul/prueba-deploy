package transporte;

import admin.FactorEmision;
import lombok.Getter;
import lombok.Setter;
import tipoconsumo.TipoConsumo;

import javax.persistence.*;

@Entity
@Getter@Setter
public class Combustible {

  @Id
  @GeneratedValue
  @Column(name = "ID_COMBUSTIBLE")
  Long id;

  @OneToOne(cascade = CascadeType.PERSIST)
  private TipoConsumo tipoConsumo;

  public Combustible() {
  }

  public Combustible(TipoConsumo tipoConsumo) {
    this.tipoConsumo = tipoConsumo;
  }

  public TipoConsumo getTipoConsumo() {
    return this.tipoConsumo;
  }

  public double obtenerValorEmision() {
    return tipoConsumo.getFactorEmision().getValor();
  }

  public FactorEmision getFactorEmision() {
    return getTipoConsumo().getFactorEmision();
  }
}
