package repos;

import cuenta.MiembroCuenta;
import cuenta.OrganizacionCuenta;
import miembro.Miembro;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
import organizacion.*;
import repositorios.RepoSolicitud;

import java.util.ArrayList;

public class RepoSolicitudTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

  RepoSolicitud repo = RepoSolicitud.getInstance();

  Organizacion org = new Organizacion("", TipoOrganizacion.EMPRESA,"","",new ArrayList<>());
  OrganizacionCuenta cuenta = new OrganizacionCuenta("aaaa","aaaa");
  Sector ventas = new Sector("ventas",new ArrayList<>());

  Miembro juan = new Miembro("juan","juan", TipoDocumento.DNI,32323,new ArrayList<>());
  MiembroCuenta cuentaJuan = new MiembroCuenta("bbb","bbbb");
  Miembro jorge = new Miembro("jorge","jorge", TipoDocumento.DNI,32323,new ArrayList<>());
  MiembroCuenta cuentaJorge = new MiembroCuenta("bbsb","bqwbbb");

  Organizacion org2 = new Organizacion("", TipoOrganizacion.EMPRESA,"","",new ArrayList<>());
  OrganizacionCuenta cuenta2 = new OrganizacionCuenta("bbsad","asdawas");
  Sector compras = new Sector("compras",new ArrayList<>());


  @BeforeEach
  public void init() {
    /*
    entityManager().getTransaction().begin();
    entityManager().persist(cuenta);
    entityManager().getTransaction().commit();

    org.setCuenta(cuenta);
    org.incorporarSector(ventas);
    entityManager().getTransaction().begin();
    entityManager().persist(org);
    entityManager().getTransaction().commit();

    entityManager().getTransaction().begin();
    entityManager().persist(cuenta2);
    entityManager().getTransaction().commit();

    org2.setCuenta(cuenta2);
    org2.incorporarSector(compras);
    entityManager().getTransaction().begin();
    entityManager().persist(org2);
    entityManager().getTransaction().commit();

    entityManager().getTransaction().begin();
    entityManager().persist(cuentaJuan);
    entityManager().getTransaction().commit();
    juan.setCuenta(cuentaJuan);
    entityManager().getTransaction().begin();
    entityManager().persist(juan);
    entityManager().getTransaction().commit();

    entityManager().getTransaction().begin();
    entityManager().persist(cuentaJorge);
    entityManager().getTransaction().commit();
    jorge.setCuenta(cuentaJorge);
    entityManager().getTransaction().begin();
    entityManager().persist(jorge);
    entityManager().getTransaction().commit();
    */

  }

  @Test
  public void puedoCargarYRecuperarUnaSolicitud() {

    Solicitud solicitud = new Solicitud(juan,ventas);
    //TODO
 //   repo.addSolicitud(solicitud);

    //Solicitud solicitudRecuperada = repo.getSolicitudById(solicitud.getId());

    //Assertions.assertEquals(solicitud,solicitudRecuperada);
  }

  @Test
  public void hay2SolicitudesDeOrg2() {

    Solicitud solicitud = new Solicitud(juan,compras);
    Solicitud solicitud1 = new Solicitud(jorge,compras);
    //TODO
    //repo.addSolicitud(solicitud1);
    //repo.addSolicitud(solicitud);

    //Assertions.assertEquals(2,repo.getSolicitudesDe(org2).size());
  }
}
