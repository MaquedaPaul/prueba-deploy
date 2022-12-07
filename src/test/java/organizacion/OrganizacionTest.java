package organizacion;

import exceptions.LaFechaDeInicioDebeSerAnteriorALaFechaDeFin;
import exceptions.LaSolicitudNoPerteneceAEstaOrganizacion;
import exceptions.NoExisteElSectorVinculante;
import exceptions.NoSeEncuentraException;

import miembro.Miembro;
import miembro.MiembroBuilder;
import notificaciones.Contacto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import organizacion.periodo.Periodo;
import organizacion.periodo.PeriodoAnual;
import organizacion.periodo.PeriodoMensual;
import transporte.Trayecto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class OrganizacionTest {

  Organizacion onu = new Organizacion("texto1", TipoOrganizacion.INSTITUCION, "texto2", "texto3", new ArrayList<>());
  Miembro jorgito = generarMiembro("jorge", "Nitales", 42222222, TipoDocumento.DNI);
  Organizacion spyOnu = spy(onu);
  Miembro spyjorgito = spy(jorgito);
//TODO
  //MedicionAntigua med1 = mock(MedicionAntigua.class);
  //MedicionAntigua med2 = mock(MedicionAntigua.class);
  //MedicionAntigua med3 = mock(MedicionAntigua.class);

  //List<MedicionAntigua> mediciones = new ArrayList<>();

  Miembro miembro1 = mock(Miembro.class);
  Miembro miembro2 = mock(Miembro.class);

  List<Miembro> miembros = new ArrayList<>();
  List<Miembro> otros = new ArrayList<>();

  Trayecto trayecto1 = mock(Trayecto.class);
  Trayecto trayecto2 = mock(Trayecto.class);
  Trayecto trayecto3 = mock(Trayecto.class);

  List<Trayecto> trayectos1 = new ArrayList<>();
  List<Trayecto> trayectos2 = new ArrayList<>();

  Periodo mensual = new PeriodoMensual(LocalDate.of(2022, 10, 3));
  Periodo anual = new PeriodoAnual(LocalDate.of(2022, 10, 3));

  PeriodoMensual dic2020 = new PeriodoMensual(LocalDate.of(2020, 12, 1));
  PeriodoMensual jul2021 = new PeriodoMensual(LocalDate.of(2021, 7, 1));

  @Test
  public void laOrganizacionIncorporaUnSector() {

    Sector compras = new Sector("Compras", new ArrayList<>());
    onu.incorporarSector(compras);
    assertEquals(onu.getSectores().size(), 1);
  }

  @Test
  public void siLaSolicitudNoPerteneceALaOrgNoPuedeProcesarla() {

    Organizacion org = new Organizacion("", TipoOrganizacion.INSTITUCION, "", "", new ArrayList<>());
    Sector sector = mock(Sector.class);
    Miembro miembro1 = mock(Miembro.class);
    Solicitud solicitud = new Solicitud(miembro1, sector);
    when(sector.getNombre()).thenReturn("ssss");

    org.incorporarSector(sector);
    assertThrows(LaSolicitudNoPerteneceAEstaOrganizacion.class,
        () -> org.procesarVinculacion(solicitud, true));

  }

  @Test
  public void siElSectorDeLaSolicitudNoPerteneceALaOrganizacionNoSePuedeAgregarALasSolicitudes() {
    Organizacion org = new Organizacion("", TipoOrganizacion.INSTITUCION, "", "", new ArrayList<>());
    Sector sector = mock(Sector.class);
    Miembro miembro1 = mock(Miembro.class);
    Solicitud solicitud = new Solicitud(miembro1, sector);
    when(sector.getNombre()).thenReturn("ssss");

    assertThrows(NoExisteElSectorVinculante.class,
        () -> org.recibirSolicitud(solicitud));
  }

  @Test
  public void laOrganizacionNoAceptaVinculacion() {

    Organizacion org = new Organizacion("", TipoOrganizacion.INSTITUCION, "", "", new ArrayList<>());
    Sector sector = mock(Sector.class);
    Miembro miembro1 = mock(Miembro.class);
    Solicitud solicitud = new Solicitud(miembro1, sector);
    when(sector.getNombre()).thenReturn("ssss");

    org.incorporarSector(sector);
    org.recibirSolicitud(solicitud);
    org.procesarVinculacion(solicitud, false);
    verify(sector, times(0)).admitirMiembro(miembro1);

  }

  @Test
  public void elHCMensualDeLosMiembrosEs2000() {
//TODO
    //mediciones.add(med1);
    //mediciones.add(med2);
    //mediciones.add(med3);

    Sector sector1 = mock(Sector.class);
    Sector sector2 = mock(Sector.class);

    spyOnu.incorporarSector(sector1);
    spyOnu.incorporarSector(sector2);
    List<Sector> sectores = new ArrayList<>();
    sectores.add(sector1);
    sectores.add(sector2);
    miembros.add(miembro1);
    otros.add(miembro2);
    when(sector1.getMiembros()).thenReturn(miembros);
    when(sector2.getMiembros()).thenReturn(otros);
    when(spyOnu.getSectores()).thenReturn(sectores);

    trayectos1.add(trayecto1);
    trayectos1.add(trayecto2);
    trayectos2.add(trayecto2);
    trayectos2.add(trayecto3);

    when(miembro1.getTrayectos()).thenReturn(trayectos1);
    when(miembro2.getTrayectos()).thenReturn(trayectos2);

    when(trayecto1.calcularHC()).thenReturn(40D);
    when(trayecto2.calcularHC()).thenReturn(30D);
    when(trayecto3.calcularHC()).thenReturn(30D);

    assertEquals(2000D, spyOnu.calcularHCTotalDeMiembros(mensual));

  }

  @Test
  public void elHCAnualDeLosMiembrosEs24000() {
    //TODO
    //mediciones.add(med1);
    //mediciones.add(med2);
    //mediciones.add(med3);

    Sector sector1 = mock(Sector.class);
    Sector sector2 = mock(Sector.class);

    spyOnu.incorporarSector(sector1);
    spyOnu.incorporarSector(sector2);
    List<Sector> sectores = new ArrayList<>();
    sectores.add(sector1);
    sectores.add(sector2);
    miembros.add(miembro1);
    otros.add(miembro2);
    when(sector1.getMiembros()).thenReturn(miembros);
    when(sector2.getMiembros()).thenReturn(otros);
    when(spyOnu.getSectores()).thenReturn(sectores);

    trayectos1.add(trayecto1);
    trayectos1.add(trayecto2);
    trayectos2.add(trayecto2);
    trayectos2.add(trayecto3);

    when(miembro1.getTrayectos()).thenReturn(trayectos1);
    when(miembro2.getTrayectos()).thenReturn(trayectos2);

    when(trayecto1.calcularHC()).thenReturn(40D);
    when(trayecto2.calcularHC()).thenReturn(30D);
    when(trayecto3.calcularHC()).thenReturn(30D);

    assertEquals(24000D, spyOnu.calcularHCTotalDeMiembros(anual));

  }

  @Test
  public void elImpactoDeJorgeEnOctubre2021EsDel20Porciento() {
    Organizacion onu = new Organizacion("texto1", TipoOrganizacion.INSTITUCION, "texto2", "texto3", new ArrayList<>());
    Organizacion spyOnu = spy(onu);

    trayectos1.add(trayecto2);
    trayectos1.add(trayecto3);
    when(spyjorgito.getTrayectos()).thenReturn(trayectos1);
    when(trayecto2.calcularHC()).thenReturn(300D);
    when(trayecto3.calcularHC()).thenReturn(100D);

    when(spyOnu.calcularHCTotal(mensual)).thenReturn(40000D);
    when(spyOnu.calcularHCTotalMediciones(any())).thenReturn(0D);
    assertEquals(20D, spyOnu.impactoDeMiembro(spyjorgito, mensual));
  }

  @Test
  public void elImpactoDeJorgeEnTodo2021EsDel16Porciento() {
    trayectos1.add(trayecto2);
    trayectos1.add(trayecto3);
    when(spyjorgito.getTrayectos()).thenReturn(trayectos1);
    when(trayecto2.calcularHC()).thenReturn(300D);
    when(trayecto3.calcularHC()).thenReturn(100D);
    when(spyOnu.calcularHCTotal(anual)).thenReturn(600000D);
    // (100 * 12 * 20 * 400) / x = 16
    assertEquals(16D, spyOnu.impactoDeMiembro(spyjorgito, anual));
  }

  @Test
  public void unaOrganizacionNoPuedeAccederALosMiembrosQueNoLePertenecen() {
    List<Miembro> miembros = new ArrayList<>();
    miembros.add(jorgito);
    Sector compras = new Sector("Compras", miembros);
    assertThrows(NoSeEncuentraException.class, () -> onu.getMiembrosEnSector(compras));
    onu.incorporarSector(compras);
    assertEquals(onu.getMiembrosEnSector(compras), compras.getMiembros());
  }

  @Test
  public void deberiaPoderCargarseUnContacto() {
    Contacto unContacto = new Contacto("Pedrito", "pedrito@gmail.com", "1122653678");
    assertEquals(onu.getContactos().size(), 0);
    onu.cargarContacto(unContacto);
    assertEquals(onu.getContactos().size(), 1);
  }

  public static Miembro generarMiembro(String nombre,
                                       String apellido,
                                       int documento,
                                       TipoDocumento unTipo) {
    MiembroBuilder nuevoMiembro = new MiembroBuilder();
    nuevoMiembro.especificarNombre(nombre);
    nuevoMiembro.especificarApellido(apellido);
    nuevoMiembro.especificarNumeroDocumento(documento);
    nuevoMiembro.especificarTipoDocumento(unTipo);
    nuevoMiembro.especificarTrayectos(new ArrayList<>());
    return nuevoMiembro.construir();
  }

  /*
  @Test
  public void elHCEntreDIC2020yJUL2021EsDe2200() {

    PeriodoMensual dic2020 = new PeriodoMensual(LocalDate.of(2020, 12, 1));
    PeriodoMensual jul2021 = new PeriodoMensual(LocalDate.of(2021, 7, 1));
    mediciones.add(med1);
    mediciones.add(med2);

    when(spyOnu.getMedicionesEntre(dic2020, jul2021)).thenReturn(mediciones);
    when(spyOnu.calcularHCMiembrosEntre(dic2020, jul2021)).thenReturn(1000D);

    when(med1.calcularHCEntre(dic2020, jul2021)).thenReturn(200D);
    when(med2.calcularHCEntre(dic2020, jul2021)).thenReturn(1000D);

    assertEquals(2200, spyOnu.calcularHCTotalEntre(dic2020, jul2021));
  }
  */

  @Test
  public void elHCTotalDeMiembrosEntreDIC2020yJUL2021Es8000() {

    PeriodoMensual dic2020 = new PeriodoMensual(LocalDate.of(2020, 12, 1));
    PeriodoMensual jul2021 = new PeriodoMensual(LocalDate.of(2021, 7, 1));

    when(spyOnu.calcularHCTotalDeMiembros(dic2020)).thenReturn(1000D);

    Assertions.assertEquals(8000D, spyOnu.calcularHCMiembrosEntre(dic2020, jul2021));
  }

  /*
  @Test
  public void elHCTotalDeMedicionesEntreDIC2020yJUL2021Es2000() {

    MedicionAntigua medMock = mock(MedicionAntigua.class);
    MedicionAntigua medMock2 = mock(MedicionAntigua.class);
    List<MedicionAntigua> listMediciones = new ArrayList<>();
    listMediciones.add(medMock2);
    listMediciones.add(medMock);
    when(spyOnu.getMedicionesEntre(dic2020, jul2021)).thenReturn(listMediciones);
    when(medMock.calcularHCEntre(dic2020, jul2021)).thenReturn(5000D);
    when(medMock2.calcularHCEntre(dic2020, jul2021)).thenReturn(3000D);
    Assertions.assertEquals(8000D, spyOnu.calcularHCMedicionesEntre(dic2020, jul2021));
  }
   */
  @Test
  public void siLaFechaDeInicioEsPosteriorALaDeFinRompe() {
    assertThrows(LaFechaDeInicioDebeSerAnteriorALaFechaDeFin.class,
        () -> spyOnu.calcularHCMedicionesEntre(jul2021, dic2020));
    assertThrows(LaFechaDeInicioDebeSerAnteriorALaFechaDeFin.class,
        () -> spyOnu.calcularHCTotalEntre(jul2021, dic2020));
    assertThrows(LaFechaDeInicioDebeSerAnteriorALaFechaDeFin.class,
        () -> spyOnu.calcularHCMiembrosEntre(jul2021, dic2020));
  }


}




