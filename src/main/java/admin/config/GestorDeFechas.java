package admin.config;

import lombok.Getter;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GestorDeFechas {

  private static GestorDeFechas instance;

  private int diasDeTrabajo = 20;

  private GestorDeFechas() {

  }

  public static GestorDeFechas getInstance() {
    if (instance == null) {
      instance = new GestorDeFechas();
    }
    return instance;
  }

  void setDiasDeTrabajo(int dias) {
    diasDeTrabajo = dias;
  }

  public String getFormatoMensual() {
    return "MM/yyyy";
  }

  public String getFormatoAnual() {
    return "yyyy";
  }

  public YearMonth getFechaActualEnFormatoMensual(LocalDate fecha) {
    return YearMonth.of(fecha.getMonthValue(), fecha.getYear());
  }

  public Year getFechaActualEnFormatoAnual(LocalDate fecha) {
    return Year.of(fecha.getYear());
  }

  public boolean cumpleConElFormatoMensual(String fecha) {

    try {
      //List<String> valores = Arrays.stream(fecha.split("/")).collect(Collectors.toList());
      //TODO no se qué debería hacer acá
      //YearMonth fechaNueva = YearMonth.of(Integer.parseInt(valores.get(1)), Integer.parseInt(valores.get(0)));
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public boolean cumpleConElFormatoAnual(String fecha) {
    if (fecha.length() != 4) {
      return false;
    }
    try {
      Year.parse(fecha);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
