package mediciones;

import lombok.Getter;
import lombok.Setter;
import organizacion.Organizacion;
import organizacion.periodo.Periodo;
import organizacion.periodo.PeriodoMensual;
import tipoconsumo.TipoConsumo;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "PERIORICIDAD")
public abstract class Medicion {

  @Id
  @GeneratedValue
  long id;

  @ManyToOne(fetch = FetchType.EAGER)
  private Organizacion organizacion;

  @ManyToOne(fetch = FetchType.EAGER)
  private TipoConsumo tipoConsumo;

  private LocalDate fecha;

  private double valor;

  public  abstract double calcularHC(Periodo periodo);

  public abstract double calcularHCEntre(PeriodoMensual inicio, PeriodoMensual fin);

  public abstract boolean esDelPeriodo(Periodo periodo);

  public abstract boolean fueRegistradaEntre(PeriodoMensual inicio, PeriodoMensual fin);

  public int getYear() {
    return this.getFecha().getYear();
  }


}
