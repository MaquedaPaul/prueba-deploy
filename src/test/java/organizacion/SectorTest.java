package organizacion;

import admin.config.GestorDeFechas;
import miembro.Miembro;
import miembro.MiembroTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import transporte.Trayecto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SectorTest {

  Miembro miembro1 = mock(Miembro.class);
  Miembro miembro2 = mock(Miembro.class);

  List<Miembro> miembros = new ArrayList<>();

  Trayecto trayecto1 = mock(Trayecto.class);
  Trayecto trayecto2 = mock(Trayecto.class);

  List<Trayecto> trayectos = new ArrayList<>();

  Sector sector = new Sector("sector prueba", miembros);

  int diasLaborales = GestorDeFechas.getInstance().getDiasDeTrabajo();

  @BeforeEach
  public void init() {
    miembros.add(miembro1);
    miembros.add(miembro2);

    trayectos.add(trayecto1);
    trayectos.add(trayecto2);

    when(miembro1.getTrayectos()).thenReturn(trayectos);
    when(miembro2.getTrayectos()).thenReturn(trayectos);

    when(trayecto1.calcularHC()).thenReturn(300.3);
    when(trayecto2.calcularHC()).thenReturn(200.2);
  }

  @Test
  public void unMiembroSeVinculaConUnSector() {

  }

  @Test
  public void alObtenerTrayectosDeLosMiembrosNoObtengoLosRepetidos() {

    assertEquals((int) sector.getTrayectosDeMiembros().count(), 2);

  }

  @Test
  public void elHCTotalDeLosMiembrosEsLaSumaDelHCDeSusTrayectosSinRepetidos() {

    assertEquals(sector.calcularHCTotalDeMiembrosPorMes(), diasLaborales * 500.5);
  }

  @Test
  public void elHCPromedioPorMiembroEsElTotalSobreLaCantidadDeMiembros() {

    assertEquals(diasLaborales * 500.5 / 2, sector.calcularPromedioHCPorMiembroPorMes());

  }

  public Miembro generarMiembro(String nombre,
                                String apellido,
                                int documento,
                                TipoDocumento unTipo) {
    return MiembroTest.generarMiembro(nombre, apellido, documento, unTipo);
  }
}