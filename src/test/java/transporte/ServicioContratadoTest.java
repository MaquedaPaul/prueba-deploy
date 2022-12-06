package transporte;

import org.junit.jupiter.api.Test;
import services.geodds.ServicioGeodds;
import services.geodds.entities.Distancia;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class ServicioContratadoTest {
  Factory unFactory = new Factory();
  TipoVehiculo tipoVehiculo = TipoVehiculo.AUTO;
  ServicioContratado unRemis = new ServicioContratado(tipoVehiculo,20);

  @Test
  public void huboInteraccionConLaAPIAlCalcularDistancia() throws IOException {
    Tramo unTramo = unFactory.crearTramoSimple(unRemis);
    ServicioGeodds geoddsMock = mock(ServicioGeodds.class);
    unRemis.setServiocioGeo(geoddsMock);
    when(geoddsMock.distancia(anyInt(), any(), anyInt(), anyInt(), any(), anyInt())).thenReturn(new Distancia(5));
    unTramo.distanciaTramo();
    verify(geoddsMock, times(1)).distancia(anyInt(), anyString(), anyInt(), anyInt(), anyString(), anyInt());
  }
}
