package transporte;

import org.junit.jupiter.api.Test;
import services.geodds.ServicioGeodds;
import services.geodds.entities.Distancia;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class PropulsionHumanaTest {
  Factory unFactory = new Factory();
  String herramientaUtilizada = "Monopatin";
  PropulsionHumana humanoConMonopatin = new PropulsionHumana(herramientaUtilizada);

  @Test
  public void creacionDeUnaPropulsionHumana() {
    PropulsionHumana unaPropulsionHumana = new PropulsionHumana("UnaHerramienta");
    assertTrue(unaPropulsionHumana.getHerramientaUtilizada().equals("UnaHerramienta"));
  }

  @Test
  public void huboInteraccionConLaAPIAlCalcularDistancia() throws IOException {
    Tramo unTramo = unFactory.crearTramoSimple(humanoConMonopatin);
    ServicioGeodds geoddsMock = mock(ServicioGeodds.class);
    humanoConMonopatin.setServiocioGeo(geoddsMock);
    when(geoddsMock.distancia(anyInt(), any(), anyInt(), anyInt(), any(), anyInt())).thenReturn(new Distancia(5));
    unTramo.distanciaTramo();
    verify(geoddsMock, times(1)).distancia(anyInt(), anyString(), anyInt(), anyInt(), anyString(), anyInt());
  }
}
