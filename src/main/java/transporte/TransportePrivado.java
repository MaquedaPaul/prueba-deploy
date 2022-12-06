package transporte;

import linea.PuntoUbicacion;
import services.geodds.ServicioDistancia;
import services.geodds.ServicioGeodds;
import services.geodds.entities.Distancia;
import utils.ServiceLocator;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.io.IOException;

@MappedSuperclass
public abstract class TransportePrivado extends Transporte {
  @Transient
  ServicioDistancia servicioGeodds = ServiceLocator.getInstance().getServicioDistancia();

  @Override
  public double distanciaEntre(PuntoUbicacion origen, PuntoUbicacion destino) throws IOException {
    Distancia distancia = servicioGeodds.distancia(
        origen.getLocalidadId(),
        origen.getCalle(),
        origen.getAltura(),
        destino.getLocalidadId(),
        destino.getCalle(),
        destino.getAltura());
    return distancia.valor;
  }

  @Override
  public boolean sePuedeCompartir() {
    return true;
  }

  public void setServiocioGeo(ServicioGeodds unServicio) {
    this.servicioGeodds = unServicio;
  }

}
