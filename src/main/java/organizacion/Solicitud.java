package organizacion;

import lombok.Getter;
import lombok.Setter;
import miembro.Miembro;

import javax.persistence.*;

@Entity(name = "Solicitud")
@Getter
@Setter
public class Solicitud {

  @Id
  @GeneratedValue
  @Column(name = "ID_SOLICITUD")

  Long id;

  @ManyToOne(cascade = CascadeType.PERSIST)
  Miembro miembroSolicitante;

  @ManyToOne(cascade = CascadeType.PERSIST)
  Sector sectorSolicitado;

  private boolean procesada;

  public Solicitud() {
  }

  public boolean estaProcesada() {
    return procesada;
  }

  public Solicitud(Miembro unMiembro, Sector unSector) {
    miembroSolicitante = unMiembro;
    sectorSolicitado = unSector;
  }

  public void aceptarVinculacion() {
    this.sectorSolicitado.admitirMiembro(this.miembroSolicitante);
    procesada = true;
  }

  public void rechazarSolicitud() {
    procesada = true;
  }

  public boolean perteneceA(Organizacion organizacion) {
    return organizacion.existeElSector(this.sectorSolicitado);
  }
}
