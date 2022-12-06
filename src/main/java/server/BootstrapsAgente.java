package server;

import admin.FactorEmision;
import cuenta.AgenteCuenta;
import cuenta.MiembroCuenta;
import cuenta.OrganizacionCuenta;
import global.Unidad;
import lectorcsv.LectorDeCsv;
import linea.LineaTransporte;
import linea.Parada;
import linea.PuntoUbicacion;
import linea.TipoTransporte;
import mediciones.Medicion;
import miembro.Miembro;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import organizacion.Organizacion;
import organizacion.Sector;
import organizacion.TipoDocumento;
import organizacion.TipoOrganizacion;
import territorio.AgenteTerritorial;
import territorio.SectorTerritorial;
import territorio.TipoSectorTerritorial;
import tipoconsumo.TipoActividad;
import tipoconsumo.TipoAlcance;
import tipoconsumo.TipoConsumo;
import transporte.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BootstrapsAgente implements WithGlobalEntityManager {

  public static void init() {

    //ORGANIZACIONES
    Organizacion papelera = new Organizacion(
        "PAPELERA S.A",
        TipoOrganizacion.EMPRESA,
        "BALCARCE 50",
        "NO SE QUE ES ESTO",
        new ArrayList<>()
    );

    Organizacion consultora = new Organizacion(
        "EpicConsulting S.R.L",
        TipoOrganizacion.EMPRESA,
        "CABILDO 2032",
        "NO SE QUE ES ESTO",
        new ArrayList<>()
    );

    Organizacion sanJose = new Organizacion(
        "E.E.S SAN JOSE",
        TipoOrganizacion.INSTITUCION,
        "JURAMENTO 1020",
        "NO SE QUE ES ESTO",
        new ArrayList<>()
    );

    PuntoUbicacion casaJuan = new PuntoUbicacion(22,"Carpintero",2020);
    PuntoUbicacion trabajo = new PuntoUbicacion(22,"Llalala",1020);
    PuntoUbicacion kiosko = new PuntoUbicacion(22,"Llalala",2900);
    PuntoUbicacion casaJorge = new PuntoUbicacion(22,"Llalala",3000);

    Parada inicio = new Parada(0,casaJuan);
    Parada fin = new Parada(30,trabajo);
    List<Parada> paradas = new ArrayList<>();
    paradas.add(inicio);
    paradas.add(fin);
    LineaTransporte linea144 = new LineaTransporte(TipoTransporte.COLECTIVO,"114",paradas,paradas);

    TipoConsumo nafta = new TipoConsumo("nafta", Unidad.LTS, TipoActividad.COMBUSTION_FIJA, TipoAlcance.EMISION_DIRECTA);
    Combustible gasolina = new Combustible(nafta);

    Transporte colectivo = new TransportePublico(linea144,60);
    colectivo.setCombustible(gasolina);
    Transporte autito = new VehiculoParticular(TipoVehiculo.AUTO,40,"Fiat 1");
    autito.setCombustible(gasolina);
    Transporte remis = new ServicioContratado(TipoVehiculo.AUTO,30);
    remis.setCombustible(gasolina);

    //TRAMOS
    Tramo casaATrabajo = new Tramo(casaJuan,trabajo,autito);
    Tramo trabajoACasa = new Tramo(trabajo,casaJuan,autito);
    Tramo trabajoAKiosko = new Tramo(trabajo,kiosko,remis);
    Tramo kioskoATrabajo = new Tramo(kiosko,trabajo,remis);
    Tramo casaATrabajo2 = new Tramo(casaJorge,trabajo,colectivo);
    Tramo trabajoACasa2 = new Tramo(trabajo,casaJorge,colectivo);


    //TRAYECTOS
    Set<Tramo> t1 = new HashSet<>();
    Set<Tramo> t2= new HashSet<>();
    Set<Tramo> t3 = new HashSet<>();
    t1.add(casaATrabajo);
    t2.add(trabajoACasa);
    t2.add(trabajoAKiosko);
    t2.add(kioskoATrabajo);
    t3.add(casaATrabajo2);
    t3.add(trabajoACasa2);

    Trayecto trayecto1 = new Trayecto(t1);
    Trayecto trayecto2 = new Trayecto(t2);
    Trayecto trayecto3 = new Trayecto(t3);

    //MIEMBROS

    List<Trayecto> trayectos1 = new ArrayList<>();
    List<Trayecto> trayectos2 = new ArrayList<>();
    List<Trayecto> trayectos3 = new ArrayList<>();

    trayectos1.add(trayecto1);
    trayectos2.add(trayecto2);
    trayectos3.add(trayecto3);

    Miembro juan = new Miembro("JUAN","PEREZ", TipoDocumento.DNI,42000000,trayectos1);
    Miembro jorge = new Miembro("JORGE","PEREZ", TipoDocumento.DNI,42000001,trayectos2);
    Miembro esteban = new Miembro("ESTEBAN","PEREZ", TipoDocumento.DNI,42000002,trayectos3);
    Miembro martin = new Miembro("MARTIN","PEREZ", TipoDocumento.DNI,42000003,new ArrayList<>());
    Miembro oscar = new Miembro("OSCAR","PEREZ", TipoDocumento.DNI,42000004,new ArrayList<>());
    Miembro julian = new Miembro("JULIAN","PEREZ", TipoDocumento.DNI,42000005,new ArrayList<>());

    MiembroCuenta cuenta1 = new MiembroCuenta("a1","a1");
    MiembroCuenta cuenta2 = new MiembroCuenta("a2","a2");
    MiembroCuenta cuenta3 = new MiembroCuenta("a3","a3");
    MiembroCuenta cuenta4 = new MiembroCuenta("a4","a4");
    MiembroCuenta cuenta5 = new MiembroCuenta("a5","a5");
    MiembroCuenta cuenta6 = new MiembroCuenta("a6","a6");

    AgenteCuenta cuenta7 = new AgenteCuenta("a7","a7");

    OrganizacionCuenta cuenta8 = new OrganizacionCuenta("a8","a8");
    OrganizacionCuenta cuenta9 = new OrganizacionCuenta("a9","a9");
    OrganizacionCuenta cuenta10 = new OrganizacionCuenta("a10","a10");

    juan.setCuenta(cuenta1);
    jorge.setCuenta(cuenta2);
    esteban.setCuenta(cuenta3);
    martin.setCuenta(cuenta4);
    oscar.setCuenta(cuenta5);
    julian.setCuenta(cuenta6);

    //SECTORES

    List<Miembro> directivos = new ArrayList<>();

    Sector direccion = new Sector(
        "DIRECCION",
        directivos
    );

    direccion.admitirMiembro(juan);
    direccion.admitirMiembro(julian);
    sanJose.incorporarSector(direccion);
    sanJose.setCuenta(cuenta8);

    Sector ventas = new Sector("VENTAS",new ArrayList<>());
    ventas.admitirMiembro(jorge);
    ventas.admitirMiembro(oscar);
    papelera.incorporarSector(ventas);
    papelera.setCuenta(cuenta9);

    Sector desarrollo = new Sector("DESARROLLO", new ArrayList<>());
    desarrollo.admitirMiembro(esteban);
    desarrollo.admitirMiembro(martin);
    consultora.incorporarSector(desarrollo);
    consultora.setCuenta(cuenta10);

    //SECTOR TERRITORIAL
    List<Organizacion> organizaciones = new ArrayList<>();

    organizaciones.add(sanJose);
    organizaciones.add(consultora);
    organizaciones.add(papelera);

    SectorTerritorial buenosAires = new SectorTerritorial(organizaciones, TipoSectorTerritorial.PROVINCIA, "Buenos Aires");
    AgenteTerritorial juanCarlos = new AgenteTerritorial(buenosAires, "juan carlos");
    juanCarlos.setCuenta(cuenta7);

    //PERSISTIR

    new Bootstraps().persistir(juanCarlos); //TODO ¿Guarda al agente con todas las otras cosas persistidas?

  }

  public void persistir(Object object) {
    entityManager().getTransaction().begin();
    entityManager().persist(object);
    entityManager().getTransaction().commit();
  }

}
