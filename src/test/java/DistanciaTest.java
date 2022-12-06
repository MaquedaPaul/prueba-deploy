import global.Unidad;
import org.junit.jupiter.api.Test;
import services.geodds.entities.Distancia;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DistanciaTest {
  @Test
  void unaDistanciaCreadaDeberiaSerCoherenteConLosDatosIngresados() {
    Distancia unaDistancia = new Distancia(150);
    assertEquals(unaDistancia.getValor(), 150);
    unaDistancia.setUnidad(Unidad.KM);
    assertEquals(unaDistancia.getUnidad(), Unidad.KM);
    unaDistancia.setValor(30);
    assertEquals(unaDistancia.getValor(), 30);
  }
}
