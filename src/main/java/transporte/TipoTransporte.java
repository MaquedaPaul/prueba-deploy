package transporte;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.List;
import java.util.stream.Collectors;

public enum TipoTransporte implements WithGlobalEntityManager {
  TRANSPORTE_PUBLICO {
    @Override
    public Transporte getTransporte(String[] queryParams) {
      List<TransportePublico> transportes;
      String transporteUtilizado = queryParams[0];
      String tipoTransporte = queryParams[1];
      String linea = queryParams[2];

      try {
        transportes = (List<TransportePublico>) entityManager()
            .createQuery("from Transporte where TRANSPORTE_UTILIZADO = TransportePublico")
            .getResultList();
      } catch (Exception e) {
        transportes = null;
      }

      Transporte transporte = transportes.stream()
          .filter(elemento -> elemento.getLineaUtilizada().getNombre().equals(linea)).findFirst().orElse(null);

      return transporte;
    }
  },

  VEHICULO_PARTICULAR {
    @Override
    public Transporte getTransporte(String[] queryParams) {
      String tipoTransporte = queryParams[1];
      String nombre = "";
      Transporte transporte;
      try {
        nombre = queryParams[2];
      } catch (Exception e) {
        transporte = null;
      }

      try {
        nombre += " " +  queryParams[3];
      } catch (Exception e) {
        // do nothing
      }

      try {
        transporte = (Transporte) entityManager()
            .createQuery("from Transporte where TIPO_TRANSPORTE = :d and nombre = :nombre")
            .setParameter("d", tipoTransporte).setParameter("nombre", nombre)
            .getResultList()
            .stream()
            .findFirst()
            .orElse(null);
      } catch (Exception e) {
        transporte = null;
      }
      return transporte;
    }
  },

  SERCICIO_CONTRATADO {
    @Override
    public Transporte getTransporte(String[] queryParams) {
      String tipoTransporte = queryParams[1];
      Transporte transporte;

      try {
        transporte = (Transporte) entityManager()
          .createQuery("from Transporte where TIPO_TRANSPORTE = :d")
          .setParameter("d", tipoTransporte)
          .getResultList()
          .stream()
          .findFirst()
          .orElse(null);
      return transporte;
    } catch (Exception e) {
        transporte = null;
      }
      return transporte;
    }
  },

  PROPULSION_HUMANA {
    @Override
    public Transporte getTransporte(String[] queryParams) {
      String tipoTransporte = queryParams[1];
      Transporte transporte =  (Transporte) entityManager()
          .createQuery("from Transporte where TIPO_TRANSPORTE = :d")
          .setParameter("d", tipoTransporte)
          .getResultList()
          .stream()
          .findFirst()
          .orElse(null);
      return transporte;
    }
  };

  public abstract Transporte getTransporte(String[] queryParams);
}
