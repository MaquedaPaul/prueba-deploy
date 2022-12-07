package organizacion;

import linea.LineaTransporte;
import linea.Parada;
import linea.PuntoUbicacion;
import linea.TipoTransporte;
import miembro.Miembro;
import notificaciones.Contacto;
import org.junit.jupiter.api.Test;
import repositorios.RepoOrganizacion;
import transporte.Tramo;
import transporte.TransportePublico;
import transporte.Trayecto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RepoOrganizacionesTest {


    PuntoUbicacion punto1 = new PuntoUbicacion(12,"salta",157);
    PuntoUbicacion punto2 = new PuntoUbicacion(12,"salta",157);
    List<Parada> paradasIda = new ArrayList<>();
    List<Parada> paradasVuelta = new ArrayList<>();
    LineaTransporte nuevaLinea =
        new LineaTransporte(TipoTransporte.COLECTIVO, "441", paradasIda, paradasVuelta);
    TransportePublico transporte = new TransportePublico(nuevaLinea, 20);
    List<Trayecto> trayectos = new ArrayList<>();
    Tramo unTramo = new Tramo(punto1, punto2, transporte);
    Set<Tramo> tramos = new HashSet<>();
    Trayecto unTrayecto = new Trayecto(tramos);
    Miembro unMiembro = new Miembro("Pedro","Rodriguez", TipoDocumento.DNI,43409129,trayectos);
    List<Miembro> miembros = new ArrayList<>();

    List<Contacto> contactos = new ArrayList<>();
    Contacto unContacto = new Contacto("Pedro", "Pedrito@gmail.com","1135032912");
    Sector unSector = new Sector("Ventas",miembros);

    // ORGANIZACIONES
    Organizacion unaOrganizacion = new Organizacion("Pedrito SRL", TipoOrganizacion.EMPRESA,"Argentina","unaClasificacion",contactos);
    Organizacion OrgGubernamental = new Organizacion("Pedrito SRL2", TipoOrganizacion.GUBERNAMENTAL,"Argentina","unaClasificacion",contactos);
    Organizacion OrgGubernamental2 = new Organizacion("Pedrito SRL3", TipoOrganizacion.GUBERNAMENTAL,"Argentina","unaClasificacion",contactos);
    Organizacion unaOrganizacionAgregada = new Organizacion("AGREGADO", TipoOrganizacion.EMPRESA,"Argentina","unaClasificacion",contactos);

    // LISTA DE ORGANIZACIONES

    List<Organizacion> listadoOrganizacionesGubernamentales = new ArrayList<>();

    {
        paradasIda.add(new Parada(700, punto1));
        paradasVuelta.add(new Parada(700, punto2));
        //tramos.add(unTramo);
        paradasIda.add(new Parada(700, punto1));
        paradasVuelta.add(new Parada(700, punto2));
        tramos.add(unTramo);
        trayectos.add(unTrayecto);
        miembros.add(unMiembro);
        contactos.add(unContacto);
        unaOrganizacion.incorporarSector(unSector);
        listadoOrganizacionesGubernamentales.add(OrgGubernamental);
        listadoOrganizacionesGubernamentales.add(OrgGubernamental2);
        //TODO
        /*
        RepoOrganizacion.getInstance().agregarOrganizacion(unaOrganizacion);
        RepoOrganizacion.getInstance().agregarOrganizacion(OrgGubernamental);
        RepoOrganizacion.getInstance().agregarOrganizacion(OrgGubernamental2);
        */

    }

    @Test
    public void sePuedeEliminarUnaOrganizacion() {
        //TODO
        /*
        RepoOrganizacion.getInstance().agregarOrganizacion(unaOrganizacionAgregada);
        List<Organizacion> organizaciones = RepoOrganizacion.getInstance().getOrganizaciones();
        Long idDelAgregado = unaOrganizacionAgregada.getId();
        assertEquals(organizaciones.size(), 1);
        RepoOrganizacion.getInstance().eliminarOrganizacion(unaOrganizacionAgregada);
        List<Organizacion> match = organizaciones.stream().filter(org -> org.equals(unaOrganizacionAgregada)).collect(Collectors.toList());
        assertEquals(match.size(), 0);
        organizaciones = RepoOrganizacion.getInstance().getOrganizaciones();
        assertEquals(organizaciones.size(), 0);
        */

    }

    @Test
    public void unaOrganizacionCargadaEnLaBaseDeberiaPoderEncontrarse() {
        //TODO
        /*
        RepoOrganizacion.getInstance().agregarOrganizacion(unaOrganizacion);
        RepoOrganizacion.getInstance().agregarOrganizacion(unaOrganizacionAgregada);
        List<Organizacion> organizaciones = RepoOrganizacion.getInstance().getOrganizaciones();
        int longitud = organizaciones.size();
        Long idDelAgregado = unaOrganizacionAgregada.getId();
        assertTrue(RepoOrganizacion.getInstance().estaPersistido(unaOrganizacionAgregada));
        assertTrue(RepoOrganizacion.getInstance().estaPersistido(unaOrganizacion));

         */
    }

    @Test
    public void sePuedeFiltrarPorTipoDeOrganizacionGubernamental() {

        Organizacion unaOrganizacion = new Organizacion("Pedrito SRL", TipoOrganizacion.EMPRESA, "Argentina", "unaClasificacion", contactos);
        Organizacion OrgGubernamental = new Organizacion("Pedrito SRL2", TipoOrganizacion.GUBERNAMENTAL, "Argentina", "unaClasificacion", contactos);
        Organizacion OrgGubernamental2 = new Organizacion("Pedrito SRL3", TipoOrganizacion.GUBERNAMENTAL, "Argentina", "unaClasificacion", contactos);
        Organizacion unaOrganizacionAgregada = new Organizacion("AGREGADO", TipoOrganizacion.EMPRESA, "Argentina", "unaClasificacion", contactos);
        List<Organizacion> listadoOrganizacionesGubernamentales = new ArrayList<>();
        listadoOrganizacionesGubernamentales.add(OrgGubernamental);
        listadoOrganizacionesGubernamentales.add(OrgGubernamental2);
        //TODO
        /*
        RepoOrganizacion.getInstance().agregarOrganizacion(OrgGubernamental);
        RepoOrganizacion.getInstance().agregarOrganizacion(unaOrganizacion);
        RepoOrganizacion.getInstance().agregarOrganizacion(OrgGubernamental2);

        List<Organizacion> listadoDeLaBase = RepoOrganizacion.getInstance()
            .getOrganizaciones()
            .stream()
            .filter(organizacion -> organizacion.getTipo().equals(TipoOrganizacion.GUBERNAMENTAL)).collect(Collectors.toList());
        assertEquals(listadoOrganizacionesGubernamentales, listadoDeLaBase);

         */
    }
}
