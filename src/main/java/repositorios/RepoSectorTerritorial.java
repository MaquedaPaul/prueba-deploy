package repositorios;

import cuenta.AgenteCuenta;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import territorio.AgenteTerritorial;
import territorio.SectorTerritorial;

import java.util.List;

public class RepoSectorTerritorial implements WithGlobalEntityManager {

    private static RepoSectorTerritorial instance;

    private RepoSectorTerritorial() {
    }

    public static RepoSectorTerritorial getInstance() {
        if (instance == null) {
            instance = new RepoSectorTerritorial();
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
    public List<SectorTerritorial> getSectoresTerritoriales() {
        return entityManager().createQuery("FROM SectorTerritorial").getResultList();
    }

    public void registrarSectorTerritorial(SectorTerritorial sector) {
        entityManager().getTransaction().begin();
        entityManager().persist(sector);
        entityManager().getTransaction().commit();
    }

    public void registrarAgente(AgenteTerritorial agente) {
        entityManager().getTransaction().begin();
        entityManager().persist(agente);
        entityManager().getTransaction().commit();
    }

    public AgenteTerritorial getAgenteByAccount(AgenteCuenta cuenta) {
        return (AgenteTerritorial) entityManager()
            .createQuery("From AgenteTerritorial " +
                "JOIN Cuenta " +
                "ON Cuenta.usuario = AgenteTerritorial.cuenta.usuario " +
                "WHERE Cuenta.usuario = :usuario ")
            .setParameter("usuario",cuenta.getUsuario())
            .getResultList().get(0);
    }

    public SectorTerritorial getSectorById(long id) {
        return entityManager().find(SectorTerritorial.class,id);
    }
}
