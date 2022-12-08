package server;

import admin.FactorEmision;
import cuenta.AgenteCuenta;
import cuenta.Cuenta;
import cuenta.MiembroCuenta;
import cuenta.OrganizacionCuenta;
import global.Unidad;
import lectorcsv.LectorMediciones;
import linea.LineaTransporte;
import linea.Parada;
import linea.PuntoUbicacion;
import linea.TipoTransporte;
import miembro.Miembro;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import organizacion.*;
import repositorios.*;
import territorio.AgenteTerritorial;
import territorio.SectorTerritorial;
import territorio.TipoSectorTerritorial;
import transporte.*;
import tipoconsumo.TipoActividad;
import tipoconsumo.TipoAlcance;
import tipoconsumo.TipoConsumo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CargaDeDatos implements WithGlobalEntityManager {

  private static final CargaDeDatos dbConnection = new CargaDeDatos();

  public static void main(String[] args) {

    persistirFactoresEmision();
    persistirTiposConsumo();
    persistirCombustibles();
    persistirLineaTransporte();
    persistirTransportes();
    persistirCuentas();
    persistirMiembrosConTrayectos();
    persistirOrganizacionesConSectores();
    persistirMediciones();
    persistirSectorTerritorial();
    persistirAgente();
    persistirSolicitudes();
    System.out.println("Terminé de persistir todo :D");

  }

  public static void persistirFactoresEmision() {
    FactorEmision kwh = new FactorEmision(0.2, Unidad.KWH);
    FactorEmision lts = new FactorEmision(0.1, Unidad.LTS);
    FactorEmision km = new FactorEmision(0.3, Unidad.KG);

    dbConnection.persistir(kwh);
    dbConnection.persistir(lts);
    dbConnection.persistir(km);
  }

  public static void persistirTiposConsumo() {
    TipoConsumo nafta = new TipoConsumo("NAFTA",Unidad.LTS, TipoActividad.COMBUSTION_FIJA, TipoAlcance.EMISION_DIRECTA);
    TipoConsumo electricidad = new TipoConsumo("ELECTRICIDAD",Unidad.KWH,TipoActividad.ELECTRICIDAD_ADQUIRIDA_CONSUMIDA,TipoAlcance.EMISION_INDIRECTA_ASOC_ELECTRICIDAD);
    TipoConsumo carbon = new TipoConsumo("CARBON",Unidad.KG,TipoActividad.COMBUSTION_FIJA,TipoAlcance.EMISION_DIRECTA);

    FactorEmision kwh = RepoFactoresEmision.getInstance().getFactorById(1);
    FactorEmision lts = RepoFactoresEmision.getInstance().getFactorById(2);
    FactorEmision km = RepoFactoresEmision.getInstance().getFactorById(3);

    nafta.setFactorEmision(lts);
    electricidad.setFactorEmision(kwh);
    carbon.setFactorEmision(km);

    dbConnection.persistir(nafta);
    dbConnection.persistir(electricidad);
    dbConnection.persistir(carbon);
  }

  public static void persistirCombustibles() {
    TipoConsumo nafta = RepoTipoDeConsumo.getInstance().getTipoConsumoById(1);
    Combustible naftaC = new Combustible(nafta);
    TipoConsumo electricidad = RepoTipoDeConsumo.getInstance().getTipoConsumoById(2);
    Combustible electricidadC = new Combustible(electricidad);

    dbConnection.persistir(naftaC);
    dbConnection.persistir(electricidadC);
  }

  public static void persistirLineaTransporte() {
    List<Parada> recorridoIda = new ArrayList<>();

    PuntoUbicacion pu1 = new PuntoUbicacion(1,"San Lorenzo",6153);
    PuntoUbicacion pu2 = new PuntoUbicacion(1,"25 de mayo",4224);
    PuntoUbicacion pu3 = new PuntoUbicacion(3,"Dante",2222);

    Parada inicioRecorrido = new Parada(0,pu1);
    Parada mitadRecorrido = new Parada(30,pu2);
    Parada finRecorrido = new Parada(45,pu3);

    recorridoIda.add(inicioRecorrido);
    recorridoIda.add(mitadRecorrido);
    recorridoIda.add(finRecorrido);

    List<Parada> recorridoVuelta = new ArrayList<>();

    PuntoUbicacion pu6 = new PuntoUbicacion(1,"San Lorenzo",6300);
    PuntoUbicacion pu5 = new PuntoUbicacion(1,"25 de mayo",4500);
    PuntoUbicacion pu4 = new PuntoUbicacion(3,"Dante",2032);

    Parada inicioRecorridoVuelta = new Parada(0,pu4);
    Parada mitadRecorridoVuelta = new Parada(28,pu5);
    Parada finRecorridoVuelta = new Parada(43,pu6);

    recorridoVuelta.add(inicioRecorridoVuelta);
    recorridoVuelta.add(mitadRecorridoVuelta);
    recorridoVuelta.add(finRecorridoVuelta);

    LineaTransporte linea144 = new LineaTransporte(TipoTransporte.COLECTIVO,"144",recorridoIda,recorridoVuelta);
    LineaTransporte linea98 = new LineaTransporte(TipoTransporte.COLECTIVO,"98",recorridoIda,recorridoVuelta);
    dbConnection.persistir(linea144);
    dbConnection.persistir(linea98);
  }

  public static void persistirTransportes() {
    LineaTransporte linea = dbConnection.getById(1,LineaTransporte.class);
    Transporte colectivo = new TransportePublico(linea,40);
    colectivo.setCombustible(dbConnection.getById(1,Combustible.class));
    dbConnection.persistir(colectivo);

    LineaTransporte linea98 = dbConnection.getById(2,LineaTransporte.class);
    Transporte colectivo98 = new TransportePublico(linea98,38);
    colectivo98.setCombustible(dbConnection.getById(1,Combustible.class));
    dbConnection.persistir(colectivo98);

    Transporte vehiculoPart = new VehiculoParticular(TipoVehiculo.AUTO,40,"FORD FIESTA");
    vehiculoPart.setCombustible(dbConnection.getById(1,Combustible.class));
    dbConnection.persistir(vehiculoPart);

    Transporte servicioContr = new ServicioContratado(TipoVehiculo.CAMIONETA,60);
    servicioContr.setCombustible(dbConnection.getById(1,Combustible.class));
    dbConnection.persistir(servicioContr);

    Transporte bicicleta = new PropulsionHumana("BICICLETA");
    bicicleta.setCombustible(dbConnection.getById(1,Combustible.class));
    dbConnection.persistir(bicicleta);


  }

  public static void persistirCuentas() {
    Cuenta cuentaJuan = new MiembroCuenta("JUANCARLOS800","GTAVDESCARGAR");
    Cuenta cuentaPedro = new MiembroCuenta("PEDROLOPEZ","PIRULOYASOCIADOS");
    Cuenta cuentaDaniel = new MiembroCuenta("Daniel_Aiz","GTAVCDESCARGAR");
    Cuenta cuentaMarcos = new MiembroCuenta("Marquitos999","VamosMessi1984");
    Cuenta cuentaPepsi = new OrganizacionCuenta("PEPSICO","COCACOLASUCKS");
    Cuenta cuentaMovistar = new OrganizacionCuenta("MOVISTAR","CLAROSUCKS");
    Cuenta cuentaBuenosAires = new AgenteCuenta("BUENOS AIRES","CORDOBASUCKS");
    //Cuenta cuentaAlmagro = new AgenteCuenta("ALMAGRO","MEDRANO 591");
    dbConnection.persistir(cuentaJuan);
    dbConnection.persistir(cuentaPedro);
    dbConnection.persistir(cuentaPepsi);
    dbConnection.persistir(cuentaMovistar);
    dbConnection.persistir(cuentaBuenosAires);
    //dbConnection.persistir(cuentaAlmagro);
    dbConnection.persistir(cuentaDaniel);
    dbConnection.persistir(cuentaMarcos);
  }

  public static void persistirMiembrosConTrayectos() {
    List<Trayecto> trayectosJuan = new ArrayList<>();

    List<Trayecto> trayectosPedro = new ArrayList<>();
    Set<Tramo> tramos = new HashSet<>();
    Set<Tramo> tramos2 = new HashSet<>();
    Set<Tramo> tramos3 = new HashSet<>();

    PuntoUbicacion pu1 = new PuntoUbicacion(1,"San Lorenzo",6153);

    //TODO ni se utiliza
    //PuntoUbicacion pu2 = new PuntoUbicacion(1,"25 de mayo",4224);
    PuntoUbicacion pu3 = new PuntoUbicacion(3,"Dante",2222);
    PuntoUbicacion pu4 = new PuntoUbicacion(3,"Dante",2900);

    Transporte vp = dbConnection.getById(3,VehiculoParticular.class);
    tramos.add(new Tramo(pu1,pu3,vp));
    Transporte c144 = dbConnection.getById(1,TransportePublico.class);
    Transporte bici = dbConnection.getById(5,PropulsionHumana.class);

    tramos2.add(new Tramo(pu1,pu3,c144));
    tramos2.add(new Tramo(pu3,pu4,bici));

    Transporte sc = dbConnection.getById(4,ServicioContratado.class);
    tramos3.add(new Tramo(pu1,pu4,sc));
    Trayecto t1 = new Trayecto(tramos);
    Trayecto t2 = new Trayecto(tramos2);
    Trayecto t3 = new Trayecto(tramos3);

    trayectosJuan.add(t1);
    trayectosJuan.add(t3);
    trayectosPedro.add(t2);
    trayectosPedro.add(t3);

    MiembroCuenta cuentaJuan = dbConnection.getById(1,MiembroCuenta.class);
    Miembro juan = new Miembro("JUAN","CARLOS", TipoDocumento.DNI,42222222,trayectosJuan);
    juan.setCuenta(cuentaJuan);

    MiembroCuenta cuentaPedro = dbConnection.getById(2,MiembroCuenta.class);
    Miembro pedro = new Miembro("PEDRO","LOPEZ", TipoDocumento.DNI,42223222,trayectosPedro);
    pedro.setCuenta(cuentaPedro);

    MiembroCuenta cuentaDaniel = dbConnection.getById(7,MiembroCuenta.class);
    Miembro daniel = new Miembro("DANIEL","AIZCORBE", TipoDocumento.DNI,42203222,trayectosPedro);
    daniel.setCuenta(cuentaDaniel);

    MiembroCuenta cuentaMarcos = dbConnection.getById(8,MiembroCuenta.class);
    Miembro marcos = new Miembro("MARCOS","PIRULO", TipoDocumento.DNI,422202222,trayectosJuan);
    marcos.setCuenta(cuentaMarcos);

    dbConnection.persistir(juan);
    dbConnection.persistir(pedro);
    dbConnection.persistir(daniel);
    dbConnection.persistir(marcos);
  }

  public static void persistirOrganizacionesConSectores() {

    Organizacion pepsiCo = new Organizacion(
        "PepsiCo S.A",
        TipoOrganizacion.EMPRESA,
        "Vedia 4090",
        "Bebidas",
        new ArrayList<>()
    );

    Organizacion movistar = new Organizacion(
        "MOVISTAR",
        TipoOrganizacion.EMPRESA,
        "25 de mayo 4224",
        "Telefonía",
        new ArrayList<>()
    );

    OrganizacionCuenta cuentaPepsi = dbConnection.getById(3,OrganizacionCuenta.class);
    pepsiCo.setCuenta(cuentaPepsi);

    OrganizacionCuenta cuentaMovistar = dbConnection.getById(4,OrganizacionCuenta.class);
    movistar.setCuenta(cuentaMovistar);

    Sector ventas = new Sector("VENTAS",new ArrayList<>());
    ventas.admitirMiembro(dbConnection.getById(4,Miembro.class));
    Sector marketing = new Sector("MARKETING",new ArrayList<>());

    Sector servicioTecnico = new Sector("SERVICIO TECNICO",new ArrayList<>());
    servicioTecnico.admitirMiembro(dbConnection.getById(3,Miembro.class));
    Sector ventas2 = new Sector("VENTAS",new ArrayList<>());
    pepsiCo.incorporarSector(ventas);
    pepsiCo.incorporarSector(marketing);
    movistar.incorporarSector(ventas2);
    movistar.incorporarSector(servicioTecnico);

    dbConnection.persistir(pepsiCo);
    dbConnection.persistir(movistar);
  }

  public static void persistirMediciones() {
    Organizacion pepsiCo = RepoOrganizacion.getInstance().getOrganizacionById(1L);
    Organizacion movistar = RepoOrganizacion.getInstance().getOrganizacionById(2L);

    LectorMediciones medicionesPepsiCo = new LectorMediciones("src/main/resources/medicionesCorrectas.csv",pepsiCo);
    LectorMediciones medicionesMovistar = new LectorMediciones("src/main/resources/medicionesCorrectas.csv",movistar);

    medicionesMovistar.leerMediciones();
    medicionesMovistar.cargarMediciones();
    medicionesPepsiCo.leerMediciones();
    medicionesPepsiCo.cargarMediciones();
  }

  public static void persistirSectorTerritorial() {
    Organizacion org1 = RepoOrganizacion.getInstance().getOrganizacionById(1L);
    Organizacion org2 = RepoOrganizacion.getInstance().getOrganizacionById(2L);

    List<Organizacion> orgs = new ArrayList<>();
    orgs.add(org1);
    orgs.add(org2);
    SectorTerritorial bsas = new SectorTerritorial(orgs, TipoSectorTerritorial.PROVINCIA,"BUENOS AIRES");
    dbConnection.persistir(bsas);
  }

  public static void persistirAgente() {
    SectorTerritorial sector = RepoSectorTerritorial.getInstance().getSectorById(1);
    AgenteCuenta cuenta = dbConnection.getById(5,AgenteCuenta.class);
    AgenteTerritorial daniel = new AgenteTerritorial(sector,"DANIEL");
    daniel.setCuenta(cuenta);
    dbConnection.persistir(daniel);
  }

  public static void persistirSolicitudes() {
    Miembro m1 = dbConnection.getById(1,Miembro.class);
    Sector s1 = dbConnection.getById(1,Sector.class);
    Miembro m2 = dbConnection.getById(2,Miembro.class);
    Sector s2 = dbConnection.getById(3,Sector.class);
    Sector s3 = dbConnection.getById(4,Sector.class);
    Solicitud solicitud1 = new Solicitud(m1,s1);
    Solicitud solicitud2 = new Solicitud(m1,s2);
    Solicitud solicitud3 = new Solicitud(m2,s3);

    dbConnection.persistir(solicitud1);
    dbConnection.persistir(solicitud2);
    dbConnection.persistir(solicitud3);
  }

  public void persistir(Object o) {
    entityManager().getTransaction().begin();
    entityManager().persist(o);
    entityManager().getTransaction().commit();
  }

  public <T> T getById(long id, Class<T> clase ) {
    return entityManager().find(clase,id);
  }
}
