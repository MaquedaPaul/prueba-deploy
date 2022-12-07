package miembro;

import exceptions.EsteTrayectoNoPuedeSerCompartido;
import linea.LineaTransporte;
import linea.PuntoUbicacion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import organizacion.*;
import organizacion.periodo.Periodo;
import organizacion.periodo.PeriodoMensual;
import transporte.PropulsionHumana;
import transporte.Tramo;
import transporte.TransportePublico;
import transporte.Trayecto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class MiembroTest {

  Organizacion org1 = new Organizacion("", TipoOrganizacion.INSTITUCION, "", "", new ArrayList<>());
  Organizacion org2 = new Organizacion("", TipoOrganizacion.EMPRESA, "", "", new ArrayList<>());
  List<Trayecto> trayectos = mock(ArrayList.class);
  Miembro miembro1 = new Miembro("", "", TipoDocumento.DNI, 1, trayectos);

  @Test
  public void unMiembroPuedeVincularseAVariasOrganizaciones() {

    Sector sector1 = new Sector("", new ArrayList<>());
    Sector sector2 = new Sector("", new ArrayList<>());
    org1.incorporarSector(sector1);
    org2.incorporarSector(sector2);

    Solicitud solicitud1 = new Solicitud(miembro1, sector1);
    Solicitud solicitud2 = new Solicitud(miembro1, sector2);
    //TODO ROMPE POR DB
    //miembro1.solicitarVinculacion(org1, solicitud1);
    //miembro1.solicitarVinculacion(org2, solicitud2);

    //Assertions.assertFalse(sector1.getMiembros().contains(miembro1));
    //Assertions.assertFalse(sector2.getMiembros().contains(miembro1));
    /*
    org1.procesarVinculacion(solicitud1, true);
    org2.procesarVinculacion(solicitud2, true);

    Assertions.assertTrue(sector1.getMiembros().contains(miembro1));
    Assertions.assertTrue(sector2.getMiembros().contains(miembro1));

     */
  }

  @Test
  public void siLaVinculacionSeRechazaElMiembroNoSeIncorporaAlSector() {

    Sector mockSector = mock(Sector.class);

    Solicitud solicitud = new Solicitud(miembro1, mockSector);

    solicitud.rechazarSolicitud();
    verify(mockSector, times(0)).admitirMiembro(miembro1);

  }

  @Test
  public void juanGenera2000HCPorMes() {
    Miembro juan = generarMiembro("juan", "juan", 2222, TipoDocumento.DNI);

    Trayecto trayecto1 = mock(Trayecto.class);
    Trayecto trayecto2 = mock(Trayecto.class);

    when(trayecto1.calcularHC()).thenReturn(40D);
    when(trayecto2.calcularHC()).thenReturn(60D);

    juan.registrarTrayecto(trayecto1);
    juan.registrarTrayecto(trayecto2);

    Periodo periodo = new PeriodoMensual(LocalDate.of(2020, 3, 3));

    Assertions.assertEquals(2000D, juan.calcularHCTotal(periodo));
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

  @Test
  public void noPuedoCompartirTrayectosQueNoSeanSoloConVehiculoParticularOServicioContratado() {

    Tramo tramoPublico = new Tramo(mock(PuntoUbicacion.class), mock(PuntoUbicacion.class), new TransportePublico(mock(LineaTransporte.class), 100));
    Tramo tramoAPie = new Tramo(mock(PuntoUbicacion.class), mock(PuntoUbicacion.class), new PropulsionHumana(""));

    Set<Tramo> tramos = new HashSet<>();
    tramos.add(tramoPublico);
    Set<Tramo> tramos1 = new HashSet<>();
    tramos1.add(tramoAPie);
    Trayecto trayectoNoCompartible = new Trayecto(tramos);
    Trayecto trayectoNoCompartible2 = new Trayecto(tramos1);
    List<Trayecto> trayectos1 = new ArrayList<>();
    trayectos1.add(trayectoNoCompartible);
    List<Trayecto> trayectos2 = new ArrayList<>();
    trayectos2.add(trayectoNoCompartible2);

    Miembro miembro1 = new Miembro("", "", TipoDocumento.DNI, 1214, trayectos2);
    Miembro miembro2 = new Miembro("", "", TipoDocumento.DNI, 1214, trayectos1);

    assertThrows(EsteTrayectoNoPuedeSerCompartido.class,
        () -> miembro1.compartirTrayectoCon(miembro2, trayectoNoCompartible2));
    assertThrows(EsteTrayectoNoPuedeSerCompartido.class,
        () -> miembro2.compartirTrayectoCon(miembro1, trayectoNoCompartible));
  }
}
