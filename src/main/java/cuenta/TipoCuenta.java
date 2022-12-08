package cuenta;

import java.util.Arrays;

public enum TipoCuenta {
  MIEMBRO() {
    @Override
    public boolean puedeAccederA(String path) {
      String[] pathsValidos = {
          ".*/",
          ".*/home",
          ".*/recomendaciones",
          ".*/home/trayectos",
          ".*/home/trayectos/registro",
          ".*/home/trayectos/registro/tramo-nuevo",
          ".*/home/trayectos/compartir",
          ".*/home/trayectos/registro/eliminar",
          ".*/home/trayectos/registro/cancelar",
          ".*/home/trayectos/registro/crear",
          ".*/home/vinculacion"
      };
      return Arrays.stream(pathsValidos).anyMatch(path::matches);
    }
  }, ORGANIZACION() {
    @Override
    public boolean puedeAccederA(String path) {
      String[] pathsValidos = {
          ".*/",
          ".*/home",
          ".*/home/vinculaciones",
          ".*/home/vinculaciones/.*/aceptar",
          ".*/home/vinculaciones/.*/rechazar",
          ".*/home/mediciones",
          ".*/home/mediciones/perse",
          ".*/home/mediciones/perse/creado",
          ".*/home/mediciones/archivo",
          ".*/home/calculadora-hc",
          ".*/home/calculadora-hc/hc-total",
          ".*/home/calculadora-hc/impacto-de-miembro/buscador",
          ".*/home/calculadora-hc/impacto-de-miembro",
          ".*/home/calculadora-hc/impacto-de-miembro/.*",
          ".*/home/calculadora-hc/indicador-hc-sector/buscador",
          ".*/home/calculadora-hc/indicador-hc-sector",
          ".*/home/calculadora-hc/indicador-hc-sector/.*"
      };
      return Arrays.stream(pathsValidos).anyMatch(path::matches);
    }
  }, AGENTE() {
    @Override
    public boolean puedeAccederA(String path) {
      String[] pathsValidos = {
          ".*/",
          ".*/home",
          ".*/home/composicion-hc",
          ".*/home/composicion-hc/grafico",
          ".*/home/evolucion-hc",
          ".*/home/evolucion-hc/grafico",
          ".*/home/organizaciones",
          ".*/home/hc-total",
          ".*/home/organizaciones/hc-tipo-organizacion/.*",
          ".*/home/organizaciones/.*/evolucion-hc/consulta",
          ".*/home/organizaciones/.*/evolucion-hc/grafico",
          ".*/home/organizaciones/.*/composicion-hc/consulta",
          ".*/home/organizaciones/.*/composicion-hc/grafico"
      };
      return Arrays.stream(pathsValidos).anyMatch(path::matches);
    }
  };

  public abstract boolean puedeAccederA(String path);
}
