package mediciones;

import exceptions.LaFechaDeInicioDebeSerAnteriorALaFechaDeFin;
import organizacion.Organizacion;
import organizacion.periodo.Periodo;
import organizacion.periodo.PeriodoMensual;
import tipoconsumo.TipoConsumo;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class MedicionMensual extends Medicion {

  public MedicionMensual(Organizacion org, TipoConsumo tipoConsumo, LocalDate fecha, double valor) {

    this.setFecha(fecha);
    this.setValor(valor);
    this.setOrganizacion(org);
    this.setTipoConsumo(tipoConsumo);
  }

  protected MedicionMensual() {

  }

  @Override
  public double calcularHC(Periodo periodo) {
    return this.getValor();
  }

  @Override
  public double calcularHCEntre(PeriodoMensual inicio, PeriodoMensual fin) {

    if (inicio.esDespuesDe(fin.getFecha())) {
      throw new LaFechaDeInicioDebeSerAnteriorALaFechaDeFin();
    }

    return this.fueRegistradaEntre(inicio, fin) ? this.getValor() : 0;
  }

  @Override
  public boolean esDelPeriodo(Periodo periodo) {
    return this.getYear() == periodo.getYear()
        && (this.getMonth() == periodo.getMonth()
        || periodo.perioricidad() == 12);
  }

  private int getMonth() {
    return this.getFecha().getMonthValue();
  }

  @Override
  public boolean fueRegistradaEntre(PeriodoMensual inicio, PeriodoMensual fin) {
    return (inicio.esAntesDe(this.getFecha())
        && fin.esDespuesDe(this.getFecha()));
  }
}
