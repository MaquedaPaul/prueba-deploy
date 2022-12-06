package tipoconsumo;

import admin.FactorEmision;
import exceptions.UnidadFeNoCorrespondienteConUnidadTipoConsumo;
import global.Unidad;

import javax.persistence.*;

@Entity
public class TipoConsumo {

  @Id
  @GeneratedValue
  private Long id;

  @Column
  private String nombre;

  @Enumerated(EnumType.STRING)
  private Unidad unidad;

  @Enumerated
  private TipoActividad actividad;

  @Enumerated
  private TipoAlcance alcance;

  @ManyToOne(fetch = FetchType.EAGER)
  private FactorEmision factorEmision;

  public TipoConsumo() {
  }

  public TipoConsumo(String nombre, Unidad unidad, TipoActividad actividad, TipoAlcance alcance) {
    this.nombre = nombre;
    this.unidad = unidad;
    this.actividad = actividad;
    this.alcance = alcance;
  }

  public Unidad unidadDeFactorEmisionPosible() {
    return unidad;
  }

  public void setFactorEmision(FactorEmision unFactorDeEmision) {
    Unidad unidadFE = unFactorDeEmision.getUnidadDivisible();
    this.comprobarUnidadValida(unidadFE);
    this.factorEmision = unFactorDeEmision;
  }

  private void comprobarUnidadValida(Unidad unidadDivisible) {
    if (!unidadDivisible.equals(this.unidadDeFactorEmisionPosible())) {
      throw new UnidadFeNoCorrespondienteConUnidadTipoConsumo(
          "La unidad del FE no se corresponde con la unidad del tipo.consumo.TipoConsumo");
    }
  }

  public String getNombre() {
    return nombre;
  }

  public TipoActividad getActividad() {
    return actividad;
  }

  public TipoAlcance getAlcance() {
    return alcance;
  }

  public FactorEmision getFactorEmision() {
    return factorEmision;
  }
}
