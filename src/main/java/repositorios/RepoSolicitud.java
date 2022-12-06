package repositorios;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import organizacion.*;

import java.util.List;

import java.util.Set;
import java.util.stream.Collectors;

public class RepoSolicitud implements WithGlobalEntityManager{
    private static RepoSolicitud repoSolicitud;

    private RepoSolicitud() {

    }

    public static RepoSolicitud getInstance() {
        if (repoSolicitud == null) {
            repoSolicitud = new RepoSolicitud();
        }

        return repoSolicitud;
    }

    public Solicitud getSolicitudById(long id) {
        return entityManager().find(Solicitud.class,id);
    }

    public void addSolicitud(Solicitud solicitud) {
        entityManager().getTransaction().begin();
        entityManager().persist(solicitud);
        entityManager().getTransaction().commit();
    }

    private List<Solicitud> getSolicitudes() {
        return entityManager().createQuery("FROM Solicitud").getResultList();
    }

    public Set<Solicitud> getSolicitudesDe(Organizacion organizacion) {
        return RepoSolicitud.getInstance()
            .getSolicitudes()
            .stream()
            .filter(s -> s.perteneceA(organizacion))
            .collect(Collectors.toSet());
    }

}
