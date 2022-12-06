package reporte.itemsreportes;

import organizacion.TipoOrganizacion;

public class HCPorTipoOrganizacion {
  double hc;
  TipoOrganizacion unTipo;

  public HCPorTipoOrganizacion(double hc, TipoOrganizacion tipo) {
    this.hc = hc;
    this.unTipo = tipo;
  }

  public double getHc() {
    return hc;
  }

  public TipoOrganizacion getUnTipo() {
    return unTipo;
  }
}
