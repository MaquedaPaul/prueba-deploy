package territorio;

import cuenta.AgenteCuenta;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class AgenteTerritorial {

  @Id
  @GeneratedValue
  private Long id;

  private String nombre;

  @OneToOne(cascade = CascadeType.PERSIST)
  SectorTerritorial sector;

  @OneToOne(cascade = CascadeType.PERSIST)
  AgenteCuenta cuenta;

  public AgenteTerritorial(SectorTerritorial unSector, String unNombre) {
    this.sector = unSector;
    this.nombre = unNombre;
  }

  public AgenteTerritorial() {

  }

  public SectorTerritorial getSectorTerritorial() {
    return sector;
  }

  public void setCuenta(AgenteCuenta cuenta) {
    this.cuenta = cuenta;
  }
}