package transporte;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class PropulsionHumana extends TransportePrivado {

  @Column(name = "TIPO_TRANSPORTE")
  private String herramientaUtilizada;

  public PropulsionHumana() {
  }

  public PropulsionHumana(String herramientaUtilizada) {
    this.herramientaUtilizada = herramientaUtilizada;
    this.consumoPorKilometro = 0;
  }

  public String getHerramientaUtilizada() {
    return herramientaUtilizada;
  }

  @Override
  public boolean sePuedeCompartir() {
    return false;
  }

  @Override
  public TipoTransporte getTipoTransporte() {
    return TipoTransporte.PROPULSION_HUMANA;
  }

  @Override
  public String getDisplay() {
    return this.herramientaUtilizada;
  }
}
