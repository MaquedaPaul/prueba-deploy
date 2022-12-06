package territorio;

import lombok.Getter;
import organizacion.Organizacion;
import organizacion.periodo.Periodo;
import organizacion.periodo.PeriodoMensual;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
public class SectorTerritorial {


  @Id
  @GeneratedValue
  private Long id;

  private String nombre;

  @OneToMany
  @JoinColumn(name = "sector_territorial_id")
  List<Organizacion> organizaciones;

  @Enumerated
  TipoSectorTerritorial tipoSectorTerritorial;

  public SectorTerritorial(
      List<Organizacion> organizaciones,
      TipoSectorTerritorial tipoSectorTerritorial, String nombre) {
    this.organizaciones = organizaciones;
    this.tipoSectorTerritorial = tipoSectorTerritorial;
    this.nombre = nombre;
  }

  public SectorTerritorial() {

  }

  public double calcularHC(Periodo periodo) {
    return organizaciones.stream().mapToDouble(unaOrg -> unaOrg.calcularHCTotal(periodo)).sum();
  }

  public void incorporarOrganizacion(Organizacion organizacion) {
    organizaciones.add(organizacion);
  }


  public double calcularHCEntre(PeriodoMensual inicio, PeriodoMensual fin) {
    return this.getOrganizaciones()
        .stream()
        .mapToDouble(org -> org.calcularHCTotalEntre(inicio, fin))
        .sum();
  }


  public double calcularHCMiembros(PeriodoMensual inicio) {
    return this.getOrganizaciones()
        .stream()
        .mapToDouble(org -> org.calcularHCTotalDeMiembros(inicio))
        .sum();
  }

  public double calcularHCMediciones(PeriodoMensual inicio) {
    return this.getOrganizaciones()
        .stream()
        .mapToDouble(org -> org.calcularHCTotalMediciones(inicio))
        .sum();
  }
}
