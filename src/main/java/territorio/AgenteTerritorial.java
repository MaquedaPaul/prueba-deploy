package territorio;

import cuenta.AgenteCuenta;

import javax.persistence.*;

@Entity
public class AgenteTerritorial {

  @Id
  @GeneratedValue
  private Long id;

  private String nombre;

  @OneToOne(cascade = CascadeType.PERSIST)
  SectorTerritorial sector;

  @OneToOne(cascade = CascadeType.PERSIST)
  AgenteCuenta cuenta;

  public AgenteTerritorial(SectorTerritorial unSector, String nombre) {
    this.sector = unSector;
    this.nombre = nombre;
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