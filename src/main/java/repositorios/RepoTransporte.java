package repositorios;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import transporte.Transporte;

import java.util.List;

public enum RepoTransporte implements WithGlobalEntityManager {
  Instance;

  public List<Transporte> queryTransportesPor(String tipoTransporte) {
    return entityManager().createQuery("from Transporte where TRANSPORTE_UTILIZADO = :d")
        .setParameter("d", tipoTransporte).getResultList();
  }
}
