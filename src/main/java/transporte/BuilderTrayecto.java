package transporte;

import com.google.common.collect.Iterables;
import exceptions.NoConcuerdaInicioYFin;
import linea.PuntoUbicacion;

import java.util.HashSet;
import java.util.Objects;

public class BuilderTrayecto {
  HashSet<Tramo> tramos = new HashSet<>();
  Tramo ultimoTramo = new Tramo();

  public BuilderTrayecto setPuntoOrigen(PuntoUbicacion puntoUbicacion) throws NoConcuerdaInicioYFin {
    ultimoTramo.setPuntoOrigen(puntoUbicacion);
    checkearInicioYFin();
    return this;
  }

  public BuilderTrayecto setPuntoDestino(PuntoUbicacion puntoUbicacion) {
    ultimoTramo.setPuntoDestino(puntoUbicacion);
    return this;
  }

  public BuilderTrayecto setTransporte(Transporte transporte) {
    ultimoTramo.setTransporteUtilizado(transporte);
    return this;
  }

  public void checkearInicioYFin() throws NoConcuerdaInicioYFin {
    if (!tramos.isEmpty() && !ultimoTramo.getPuntoOrigen().esIgualA(Iterables.getLast(tramos).getPuntoDestino())) {
      throw new NoConcuerdaInicioYFin("Inicio y fin no concuerdan");
    }
  }

  public void agregarTramo() {
    Objects.requireNonNull(ultimoTramo.getPuntoOrigen());
    Objects.requireNonNull(ultimoTramo.getPuntoDestino());
    Objects.requireNonNull(ultimoTramo.getTransporteUtilizado());
    tramos.add(ultimoTramo);
    ultimoTramo = new Tramo();
  }

  public Trayecto build() {
    return new Trayecto(tramos);
  }

  public HashSet<Tramo> getTramos() {
    return tramos;
  }

  public void eliminarUltimoTramo() {
    Tramo tramo = Iterables.getLast(tramos);
    tramos.remove(tramo);
  }
}
