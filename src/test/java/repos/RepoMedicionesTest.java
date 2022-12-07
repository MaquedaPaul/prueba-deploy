package repos;

import admin.FactorEmision;
import cuenta.OrganizacionCuenta;
import global.Unidad;
import mediciones.Medicion;
import mediciones.MedicionAnual;
import mediciones.MedicionMensual;
import repositorios.RepoMediciones;
import org.junit.jupiter.api.*;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
import organizacion.Organizacion;
import organizacion.TipoOrganizacion;
import organizacion.periodo.PeriodoMensual;
import tipoconsumo.TipoActividad;
import tipoconsumo.TipoAlcance;
import tipoconsumo.TipoConsumo;

import java.time.LocalDate;
import java.util.ArrayList;

public class RepoMedicionesTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

    RepoMediciones repo = RepoMediciones.getInstance();
    Organizacion organizacion = new Organizacion("dd", TipoOrganizacion.EMPRESA,"","",new ArrayList<>());
    TipoConsumo tipoConsumo = new TipoConsumo("consumo", Unidad.LTS, TipoActividad.COMBUSTION_FIJA, TipoAlcance.EMISION_DIRECTA);

    LocalDate fecha = LocalDate.now();
    OrganizacionCuenta orgCuenta = new OrganizacionCuenta("aaa","aaa");
    OrganizacionCuenta orgCuenta2 = new OrganizacionCuenta("bbb","bbb");
    Organizacion org1 = new Organizacion("otra", TipoOrganizacion.EMPRESA,"","",new ArrayList<>());
    FactorEmision factor = new FactorEmision(20,Unidad.LTS);

  LocalDate dic2020  = LocalDate.of(2020,12,15);
  LocalDate oct2020  = LocalDate.of(2020,10,15);
  LocalDate ene2021  = LocalDate.of(2021,1,15);
  LocalDate mar2021  = LocalDate.of(2021,3,15);
  LocalDate jul2021  = LocalDate.of(2021,7,15);

  Medicion medicionDic2020 = new MedicionMensual(org1,tipoConsumo,dic2020,100);
  Medicion medicionEne2021 = new MedicionMensual(org1,tipoConsumo,ene2021,100);
  Medicion medicionMar2021 = new MedicionAnual(org1,tipoConsumo,mar2021,100);
  Medicion medicionJul2021 = new MedicionAnual(org1,tipoConsumo,jul2021,100);
  Medicion medicionOct2020 = new MedicionMensual(org1,tipoConsumo,oct2020,100);

    @BeforeEach
    public void init() {
        /*
      entityManager().getTransaction().begin();
      entityManager().persist(factor);
      tipoConsumo.setFactorEmision(factor);
      entityManager().persist(orgCuenta);
      entityManager().persist(orgCuenta2);
      org1.setCuenta(orgCuenta2);
      organizacion.setCuenta(orgCuenta);
      entityManager().persist(organizacion);
      entityManager().persist(org1);
      entityManager().persist(tipoConsumo);
      entityManager().getTransaction().commit();
      */

    }

    @Test
    public void puedoPersistirYRecuperarUnaMedicionAnual() {
      Medicion medicionAnual = new MedicionAnual(organizacion, tipoConsumo,fecha,2000D);
        //TODO
        /*
      repo.cargarMedicion(medicionAnual);

      Assertions.assertNotEquals(medicionAnual.getId(),0L);

      Medicion medicionRecuperada = repo.getMedicionById(medicionAnual.getId());

      Assertions.assertEquals(medicionAnual,medicionRecuperada);
*/
    }

  @Test
  public void puedoPersistirYRecuperarUnaMedicionMensual() {
    Medicion medicionMensual = new MedicionMensual(org1, tipoConsumo,fecha,2000D);
        //TODO
      /*
    repo.cargarMedicion(medicionMensual);

    Assertions.assertNotEquals(medicionMensual.getId(),0L);

    Medicion medicionRecuperada = repo.getMedicionById(medicionMensual.getId());

    Assertions.assertEquals(medicionMensual,medicionRecuperada);
*/
  }

  @Test
  public void puedoObtenerLasMedicionesDeUnaOrg() {

      Medicion medicionAnual = new MedicionAnual(organizacion,tipoConsumo,fecha,200);
      Medicion medicionAnualDeOtra = new MedicionAnual(org1,tipoConsumo,fecha,200);
      Medicion medicionMensual = new MedicionMensual(organizacion,tipoConsumo,fecha,200);
        //TODO
      /*
      entityManager().getTransaction().begin();
      entityManager().persist(medicionAnual);
      entityManager().persist(medicionAnualDeOtra);
      entityManager().persist(medicionMensual);
      entityManager().getTransaction().commit();

      Assertions.assertFalse(repo.medicionesDe(organizacion).contains(medicionAnualDeOtra));
      Assertions.assertEquals(2,repo.medicionesDe(organizacion).size());
      */

  }

  @Test
  public void hay4MedicionesEntredic2020Yjul2021() {
        //TODO
        /*
      entityManager().getTransaction().begin();
      entityManager().persist(medicionDic2020);
      entityManager().persist(medicionEne2021);
      entityManager().persist(medicionMar2021);
      entityManager().persist(medicionJul2021);
      entityManager().persist(medicionOct2020);
      entityManager().getTransaction().commit();

      PeriodoMensual periodoInicio = new PeriodoMensual(dic2020);
      PeriodoMensual periodoFin = new PeriodoMensual(jul2021);

      Assertions.assertEquals(4,repo.getMedicionesEntre(periodoInicio,periodoFin).size());
*/
  }
}
