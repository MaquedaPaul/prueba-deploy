package lectorcsv;

import mediciones.Medicion;
import mediciones.MedicionAnual;
import mediciones.MedicionMensual;
import organizacion.Organizacion;
import tipoconsumo.TipoConsumo;

import java.time.LocalDate;

public enum TipoPerioricidad {
  ANUAL() {
    @Override
    public Medicion buildMedicion(Organizacion org, TipoConsumo tipo, LocalDate fecha, double valor) {
      return new MedicionAnual(org,tipo,fecha,valor);
    }

  },

  MENSUAL() {
    @Override
    public Medicion buildMedicion(Organizacion org, TipoConsumo tipo, LocalDate fecha, double valor) {
      return new MedicionMensual(org,tipo,fecha,valor);
    }
  };

  public abstract Medicion buildMedicion(Organizacion org, TipoConsumo tipo, LocalDate fecha, double valor);
}
