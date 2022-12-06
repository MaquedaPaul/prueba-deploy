package transporte;

import linea.LineaTransporte;
import linea.Parada;
import linea.PuntoUbicacion;
import linea.TipoTransporte;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransportePublicoTest {


  // PUNTOS DE UBICACION

  PuntoUbicacion punto1 = new PuntoUbicacion(12,"salta",157);
  PuntoUbicacion punto2 = new PuntoUbicacion(12,"salta",170);
  PuntoUbicacion punto3 = new PuntoUbicacion(12,"salta",200);

  // PARADAS

  Parada ubicacionInicio = new Parada(0, punto1);
  Parada ubicacionIntermedia = new Parada(1,punto2);
  Parada ubicacionFinal = new Parada(2, punto3);
  Parada otraUbicacion = new Parada(3, punto1);

  // LISTA DE UbicacionS
  List<Parada> paradasDeIdaDel138 = new ArrayList<>();
  List<Parada> paradasDeVueltaDel138 = new ArrayList<>();

  // LINEA TRANSPORTE
  LineaTransporte linea138 =
      new LineaTransporte(TipoTransporte.COLECTIVO, "linea138", paradasDeIdaDel138, paradasDeVueltaDel138);
  // COLECTIVO DE EJEMPLO
  TransportePublico unColectivo = new TransportePublico(linea138, 20);

  @Test
  public void elTipoDeTransporteDeUnColectivoEsCOLECTIVO() {
    assertEquals(unColectivo.getTransporteInvolucrado(), TipoTransporte.COLECTIVO);
  }

  @Test
  public void elInicioDelRecorridoDelColectivoEsSuPrimerUbicacion() {
    paradasDeIdaDel138.add(ubicacionInicio);
    paradasDeIdaDel138.add(ubicacionIntermedia);
    paradasDeIdaDel138.add(ubicacionFinal);

    assertEquals(unColectivo.getUbicacionInicioPrimerRecorrido(), ubicacionInicio);
  }

  @Test
  public void elFinalDelRecorridoDelColectivoEsSuUltimaUbicacion() {
    paradasDeIdaDel138.add(ubicacionInicio);
    paradasDeIdaDel138.add(ubicacionIntermedia);
    paradasDeIdaDel138.add(ubicacionFinal);

    assertEquals(unColectivo.getUltimaUbicacionPrimerRecorrido(), ubicacionFinal);
  }

  //REVISAR
  @Test
  public void sePuedeAgregarUnaParadaAUnaLineaExistente() {

    LineaTransporte linea138 =
        new LineaTransporte(TipoTransporte.COLECTIVO, "linea138", paradasDeIdaDel138, paradasDeVueltaDel138);
    paradasDeIdaDel138.add(ubicacionInicio);
    paradasDeIdaDel138.add(ubicacionIntermedia);
    paradasDeIdaDel138.add(ubicacionFinal);

    assertEquals(linea138.getRecorridoDeIda().size(), 3);
    linea138.agregarParadaAlRecorrido(otraUbicacion,true);
    assertEquals(linea138.getRecorridoTotal().size(), 4);
  }

  @Test
  public void sePuedeCalcularLaDistanciaEntre2Paradas() throws IOException {
    LineaTransporte linea138 =
        new LineaTransporte(TipoTransporte.COLECTIVO, "linea138", paradasDeIdaDel138, paradasDeVueltaDel138);

    paradasDeIdaDel138.add(ubicacionInicio);
    paradasDeIdaDel138.add(ubicacionIntermedia);
    paradasDeIdaDel138.add(ubicacionFinal);

    TransportePublico bondi138 = new TransportePublico(linea138, 20);

    assertEquals(bondi138.distanciaEntre(punto1, punto3), 2);

  }
}
