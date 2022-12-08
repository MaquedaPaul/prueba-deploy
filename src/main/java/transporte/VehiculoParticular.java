package transporte;

//import java.io.IOException;
//import linea.PuntoUbicacion;
//import services.geodds.ServicioGeodds;
//import services.geodds.entities.Distancia;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class VehiculoParticular extends TransportePrivado {

  @Enumerated(EnumType.STRING)
  @Column(name = "TIPO_TRANSPORTE")
  private TipoVehiculo tipoVehiculo;
  private String nombre;

  public VehiculoParticular() {
  }

  public VehiculoParticular(TipoVehiculo tipoVehiculo, double consumoPorKilometro, String nombre) {
    this.tipoVehiculo = tipoVehiculo;
    this.consumoPorKilometro = consumoPorKilometro;
    this.nombre = nombre;
  }

  public TipoVehiculo getTipoVehiculo() {
    return tipoVehiculo;
  }

  public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
    this.tipoVehiculo = tipoVehiculo;
  }

  @Override
  public TipoTransporte getTipoTransporte() {
    return TipoTransporte.VEHICULO_PARTICULAR;
  }

  @Override
  public String getDisplay() {
    return this.tipoVehiculo + " " + this.nombre;
  }
}
