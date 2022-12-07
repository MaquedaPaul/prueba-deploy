package repositorios;

import lectorcsv.RepoTipoConsumo;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import tipoconsumo.TipoConsumo;

import java.util.List;
import java.util.stream.Collectors;

public class RepoTipoDeConsumo implements WithGlobalEntityManager, RepoTipoConsumo {

  private static RepoTipoDeConsumo repoTipoConsumo = null;

  private RepoTipoDeConsumo() {
  }

  public static RepoTipoDeConsumo getInstance() {
    if (repoTipoConsumo == null) {
      repoTipoConsumo = new RepoTipoDeConsumo();
    }
    return repoTipoConsumo;
  }

  public void agregarNuevoTipoDeConsumo(TipoConsumo nuevoTipoConsumo) {
    entityManager().getTransaction().begin();
    entityManager().persist(nuevoTipoConsumo);
    entityManager().getTransaction().commit();
  }

  @SuppressWarnings("unchecked")
  public List<TipoConsumo> getTiposConsumo() {
    return entityManager().createQuery("from TipoConsumo").getResultList();
  }

  public boolean existeElTipoDeConsumo(String tipoConsumo) {

    return this.getTiposConsumo().stream().anyMatch(t -> t.getNombre().toUpperCase().equals(tipoConsumo.toUpperCase()));

  }

  public TipoConsumo getTipoConsumo(String tipoConsumo) {

    return this.getTiposConsumo().stream()
        .filter(tipo -> tipo.getNombre().equalsIgnoreCase(tipoConsumo))
        .findAny().orElse(null);
  }

  public TipoConsumo getTipoConsumoById(long id) {
    return entityManager().find(TipoConsumo.class,id);
  }

  }