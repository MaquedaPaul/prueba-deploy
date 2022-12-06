package miembro;

import linea.LineaTransporte;
import linea.PuntoUbicacion;
import org.junit.jupiter.api.Test;
import transporte.*;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TrayectoTest {

  PuntoUbicacion origen = new PuntoUbicacion(0, "mercedes", 23);
  PuntoUbicacion destino = new PuntoUbicacion(35, "libertador", 232);
  ;
  PropulsionHumana bicicleta = new PropulsionHumana("Bicicleta");
  Tramo unTramo = new Tramo(origen, destino, bicicleta);

  @Test
  void unTramoAgregadoDeberiaAparecerEnLaListaTramos() {
    Set<Tramo> unosTramos = new HashSet<>();
    Trayecto unTrayecto = new Trayecto(unosTramos);
    assertFalse(unosTramos.contains(unTramo));
    unTrayecto.agregarTramo(unTramo);
    assertTrue(unosTramos.contains(unTramo));
  }

  @Test
  void laDistanciaTotalDeUnTrayectoEsLaSumaDeSusTramos() {

    Tramo unTramoMock = mock(Tramo.class);
    Tramo otroTramoMock = mock(Tramo.class);

    when(unTramoMock.distanciaTramo()).thenReturn(100.0);
    when(otroTramoMock.distanciaTramo()).thenReturn(50.0);
    Trayecto unTrayecto = new Trayecto(new HashSet<>());
    unTrayecto.agregarTramo(unTramoMock);
    unTrayecto.agregarTramo(otroTramoMock);
    unTramoMock.distanciaTramo();
    otroTramoMock.distanciaTramo();
    assertEquals(unTrayecto.distanciaTotal(), 150);

  }

  @Test
  public void unTrayectoPuedeSerCompartidoSoloSiEsCompletamenteEnServicioContratadoOVehiculoParticular() {

    TransportePrivado servicioContratado = new ServicioContratado(TipoVehiculo.AUTO, 111);
    TransportePrivado vehiculoParticular = new VehiculoParticular(TipoVehiculo.AUTO, 3232,"Lamborghini");
    TransportePublico colectivo = new TransportePublico(mock(LineaTransporte.class), 100);
    PropulsionHumana aPie = new PropulsionHumana("");

    Tramo tramoServicioContratado = new Tramo(mock(PuntoUbicacion.class), mock(PuntoUbicacion.class), servicioContratado);
    Tramo tramoVehiculoParticular = new Tramo(mock(PuntoUbicacion.class), mock(PuntoUbicacion.class), vehiculoParticular);
    Tramo tramoPublico1 = new Tramo(mock(PuntoUbicacion.class), mock(PuntoUbicacion.class), colectivo);
    Tramo tramoAPie = new Tramo(mock(PuntoUbicacion.class), mock(PuntoUbicacion.class), aPie);

    Set<Tramo> tramoDePublicos = new HashSet<>();
    tramoDePublicos.add(tramoPublico1);
    tramoDePublicos.add(tramoAPie);
    Set<Tramo> tramos = new HashSet<>();
    Set<Tramo> tramos2 = new HashSet<>();
    tramos.add(tramoServicioContratado);
    tramos2.add(tramoVehiculoParticular);
    Trayecto trayecto1 = new Trayecto(tramos);
    Trayecto trayecto2 = new Trayecto(tramos2);
    Trayecto trayectoIncompartible = new Trayecto(tramoDePublicos);

    assertTrue(trayecto1.sePuedeCompartir());
    assertTrue(trayecto2.sePuedeCompartir());
    assertFalse(trayectoIncompartible.sePuedeCompartir());
  }


}
