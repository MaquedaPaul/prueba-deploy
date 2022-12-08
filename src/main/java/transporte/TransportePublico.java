package transporte;

import linea.LineaTransporte;
import linea.Parada;
import linea.PuntoUbicacion;
import linea.TipoTransporte;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.IOException;
import java.util.stream.Collectors;

@Entity
public class TransportePublico extends Transporte {

  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "ID_LINEA")
  private LineaTransporte lineaUtilizada;

  public TransportePublico() {
  }

  public TransportePublico(LineaTransporte lineaUtilizada, double consumoPorKilometro) {
    this.lineaUtilizada = lineaUtilizada;
    this.consumoPorKilometro = consumoPorKilometro;
  }

  public LineaTransporte getLineaUtilizada() {
    return lineaUtilizada;
  }

  public TipoTransporte getTransporteInvolucrado() {
    return lineaUtilizada.transporte();
  }

  public Parada getUbicacionInicioPrimerRecorrido() {
    return lineaUtilizada.inicioDelRecorridoDeIda();
  }

  public Parada getUltimaUbicacionPrimerRecorrido() {
    return lineaUtilizada.finalDelRecorridoDeIda();
  }

  public Parada getUltimaUbicacionRecorridoVuelta() {
    return lineaUtilizada.finalDelRecorridoDeRegreso();
  }

  public Parada getPrimeraUbicacionRecorridoVuelta() {
    return lineaUtilizada.inicioDelRecorridoDeRegreso();
  }

  public double distanciaEntre(PuntoUbicacion origen, PuntoUbicacion destino) throws IOException {
    Parada primeraParada = this.encontrarParada(origen);
    Parada segundaParada = this.encontrarParada(destino);
    return Math.abs(primeraParada.getKmActual() - segundaParada.getKmActual());
  }

  public Parada encontrarParada(PuntoUbicacion ubicacion) {
    return this.lineaUtilizada
        .getRecorridoTotal()
        .stream()
        .filter(unaParada -> esElMismo(ubicacion, unaParada.getPuntoUbicacion()))
        .collect(Collectors.toList())
        .get(0);
  }

  //conviene usar equals
  public boolean esElMismo(PuntoUbicacion ubicacion, PuntoUbicacion puntoUbicacionParada) {
    return ubicacion.esIgualA(puntoUbicacionParada);
  }

  @Override
  public transporte.TipoTransporte getTipoTransporte() {
    return transporte.TipoTransporte.TRANSPORTE_PUBLICO;
  }

  @Override
  public String getDisplay() {
    return this.lineaUtilizada.diplay();
  }
}


