package server;

import global.Unidad;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import tipoconsumo.TipoActividad;
import tipoconsumo.TipoAlcance;
import tipoconsumo.TipoConsumo;

public class BootstrapsTiposDeConsumo implements WithGlobalEntityManager {
  public static void init() {

    TipoConsumo tipoConsumoGasNatural = new TipoConsumo("Gas natural", Unidad.M3, TipoActividad.COMBUSTION_FIJA, TipoAlcance.EMISION_DIRECTA);
    TipoConsumo tipoConsumoDiesel = new TipoConsumo("Diesel",Unidad.LT,TipoActividad.COMBUSTION_FIJA,TipoAlcance.EMISION_DIRECTA);
    TipoConsumo tipoConsumoNafta = new TipoConsumo("Nafta",Unidad.LT,TipoActividad.COMBUSTION_FIJA,TipoAlcance.EMISION_DIRECTA);
    TipoConsumo tipoConsumoCarbon = new TipoConsumo("Carbon",Unidad.KG,TipoActividad.COMBUSTION_FIJA,TipoAlcance.EMISION_DIRECTA);
    TipoConsumo tipoConsumoGasoilConsumido = new TipoConsumo("Gasoil consumido",Unidad.LTS,TipoActividad.COMBUSTION_MOVIL,TipoAlcance.EMISION_DIRECTA);
    TipoConsumo tipoConsumoNaftaConsumida = new TipoConsumo("Nafta consumida",Unidad.LTS,TipoActividad.COMBUSTION_MOVIL,TipoAlcance.EMISION_DIRECTA);
    TipoConsumo tipoConsumoElectricidad = new TipoConsumo("Electricidad",Unidad.KWH,TipoActividad.ELECTRICIDAD_ADQUIRIDA_CONSUMIDA,TipoAlcance.EMISION_INDIRECTA_ASOC_ELECTRICIDAD);
    TipoConsumo tipoConsumoCamionDeCargaOUtilitario = new TipoConsumo("TransporteUtilitario",null,TipoActividad.LOGISTICA_PRODUCTOS_RESIDUOS,TipoAlcance.OTRA_EMISION_INDIRECTA);
    TipoConsumo tipoConsumoDistanciaMediaRecorrida = new TipoConsumo("DistanciaMediaRecorrida",Unidad.KM,TipoActividad.LOGISTICA_PRODUCTOS_RESIDUOS,TipoAlcance.OTRA_EMISION_INDIRECTA);

    new Bootstraps().persistir(tipoConsumoGasNatural);
    new Bootstraps().persistir(tipoConsumoDiesel);
    new Bootstraps().persistir(tipoConsumoNafta);
    new Bootstraps().persistir(tipoConsumoCarbon);
    new Bootstraps().persistir(tipoConsumoGasoilConsumido);
    new Bootstraps().persistir(tipoConsumoNaftaConsumida);
    new Bootstraps().persistir(tipoConsumoElectricidad);
    new Bootstraps().persistir(tipoConsumoCamionDeCargaOUtilitario);
    new Bootstraps().persistir(tipoConsumoDistanciaMediaRecorrida);

  }

  public void persistir(Object object) {
    entityManager().getTransaction().begin();
    entityManager().persist(object);
    entityManager().getTransaction().commit();
  }
}
