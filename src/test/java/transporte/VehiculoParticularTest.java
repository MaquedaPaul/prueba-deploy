package transporte;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.geodds.GeoddsService;
import services.geodds.ServicioGeodds;
import services.geodds.entities.Distancia;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class VehiculoParticularTest {
  Factory unFactory = new Factory();

  @BeforeEach
  void init() {
    GeoddsService geoddsService;
    geoddsService = mock(GeoddsService.class);
  }

  @Test
  public void darDeAltaUnAutoANafta() {
    TipoVehiculo esUnAuto = TipoVehiculo.AUTO;
    //TipoCombustible andaANafta = TipoCombustible.NAFTA;
    VehiculoParticular autoANafta = new VehiculoParticular(TipoVehiculo.AUTO,20,"Fiat 500");
    autoANafta.getTipoVehiculo().equals(esUnAuto);
    assertEquals(autoANafta.getTipoVehiculo(), esUnAuto);
    //assertEquals(autoANafta.getCombustible(), andaANafta);
  }

  @Test
  public void darDeAltaUnaMotoAGasoil() {
    TipoVehiculo esUnaMoto = TipoVehiculo.MOTO;
    VehiculoParticular motoAGasoil = new VehiculoParticular(esUnaMoto,20,"Yamaha");
    assertEquals(motoAGasoil.getTipoVehiculo(), esUnaMoto);
    //assertEquals(motoAGasoil.getCombustible(), );
  }

  @Test
  public void huboInteraccionConLaAPIAlCalcularDistancia() throws IOException {
    TipoVehiculo esUnaMoto = TipoVehiculo.MOTO;
    VehiculoParticular motoAGasoil = new VehiculoParticular(esUnaMoto,20,"Yamaha");
    Tramo unTramo = unFactory.crearTramoSimple(motoAGasoil);
    ServicioGeodds geoddsMock = mock(ServicioGeodds.class);
    motoAGasoil.setServiocioGeo(geoddsMock);
    when(geoddsMock.distancia(anyInt(), any(), anyInt(), anyInt(), any(), anyInt())).thenReturn(new Distancia(5));
    unTramo.distanciaTramo();
    verify(geoddsMock, times(1)).distancia(anyInt(), anyString(), anyInt(), anyInt(), anyString(), anyInt());
  }
}
