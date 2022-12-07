package reporte;

import organizacion.Organizacion;
import organizacion.TipoOrganizacion;
import organizacion.periodo.GeneradorDePeriodos;
import organizacion.periodo.PeriodoMensual;
import repositorios.RepoOrganizacion;
import reporte.itemsreportes.ComposicionHCOrganizacion;
import reporte.itemsreportes.EvolucionHCOrganizacion;
import reporte.itemsreportes.HCPorTipoOrganizacion;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReporteOrganizaciones {

  private final RepoOrganizacion repoOrganizaciones;

  public ReporteOrganizaciones(RepoOrganizacion repo) {
    this.repoOrganizaciones = repo;
  }

  public List<EvolucionHCOrganizacion> evolucionHCEntre(Organizacion org, PeriodoMensual inicio, PeriodoMensual fin) {

    List<PeriodoMensual> periodos = new GeneradorDePeriodos().generarPeriodosMensualesEntre(inicio, fin);

    return periodos.stream()
        .map(periodo -> this.generarItemDeEvolucionHC(org, periodo))
        .collect(Collectors.toList());
  }

  private EvolucionHCOrganizacion generarItemDeEvolucionHC(Organizacion org, PeriodoMensual periodo) {
    return new EvolucionHCOrganizacion(org, periodo, org.calcularHCTotal(periodo));
  }

  public List<HCPorTipoOrganizacion> hcPorTipoOrganizacion(PeriodoMensual inicio, PeriodoMensual fin) {

    List<TipoOrganizacion> tipos = Arrays.asList(TipoOrganizacion.values());

    return tipos
        .stream()
        .map(tipo -> this.calcularHCDelTipoDeOrganizacion(tipo, inicio, fin))
        .collect(Collectors.toList());
  }

  private HCPorTipoOrganizacion calcularHCDelTipoDeOrganizacion(TipoOrganizacion tipo, PeriodoMensual inicio, PeriodoMensual fin) {

    double total = repoOrganizaciones.getOrganizacionesDelTipo(tipo)
        .stream()
        .mapToDouble(org -> org.calcularHCTotalEntre(inicio, fin))
        .sum();

    return new HCPorTipoOrganizacion(total, tipo);
  }

  public List<ComposicionHCOrganizacion> composicionHCEntre(Organizacion org, PeriodoMensual inicio, PeriodoMensual fin) {

    List<PeriodoMensual> periodos = new GeneradorDePeriodos().generarPeriodosMensualesEntre(inicio, fin);

    return periodos.stream()
        .map(periodo -> this.composicionHC(org, periodo))
        .collect(Collectors.toList());
  }

  public ComposicionHCOrganizacion composicionHC(Organizacion org,PeriodoMensual periodo) {

    return new ComposicionHCOrganizacion(org,
        org.calcularHCTotalDeMiembros(periodo),
        org.calcularHCTotalMediciones(periodo),
        periodo);
  }

}
