package repositorios;

import admin.FactorEmision;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.List;

public class RepoFactoresEmision implements WithGlobalEntityManager {

  private static RepoFactoresEmision repoFactoresEmision = null;

  private RepoFactoresEmision() {
  }

  public static RepoFactoresEmision getInstance() {
    if (repoFactoresEmision == null) {
      repoFactoresEmision = new RepoFactoresEmision();
    }
    return repoFactoresEmision;
  }

  public void incorporarFactor(FactorEmision nuevoFactor) {
    entityManager().getTransaction().begin();
    entityManager().persist(nuevoFactor);
    entityManager().getTransaction().commit();
  }

  public void modificarFactorEmision(FactorEmision unFactor, double nuevoValor) {
    FactorEmision factorAModificar = entityManager().find(FactorEmision.class, unFactor.getId());
    factorAModificar.setValor(nuevoValor);
    entityManager().merge(unFactor);
  }

  @SuppressWarnings("unchecked")
  public List<FactorEmision> getFactoresEmision() {
    return entityManager().createQuery("from FactorEmision").getResultList();
  }

  public int factoresTotales() {
    return getFactoresEmision().size();
  }

  public FactorEmision getFactorById(long id) {
    return entityManager().find(FactorEmision.class,id);
  }
}
