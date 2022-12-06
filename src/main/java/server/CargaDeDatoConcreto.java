package server;

import admin.FactorEmision;
import global.Unidad;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import tipoconsumo.TipoActividad;
import tipoconsumo.TipoAlcance;
import tipoconsumo.TipoConsumo;

public class CargaDeDatoConcreto implements WithGlobalEntityManager {

    private static final CargaDeDatoConcreto dbConnection = new CargaDeDatoConcreto();

    public static void main(String[] args) {

        System.out.println("Terminé de persistir todo :D");
    }

    /*
    {
        FactorEmision m3 = new FactorEmision(0.3, Unidad.M3);
        dbConnection.persistir(m3);
        TipoConsumo gasNatural = new TipoConsumo("Gas natural", Unidad.M3, TipoActividad.COMBUSTION_FIJA, TipoAlcance.EMISION_DIRECTA);
        gasNatural.setFactorEmision(m3);
        dbConnection.persistir(gasNatural);
                FactorEmision lt = new FactorEmision(0.3, Unidad.LT);
        dbConnection.persistir(lt);
        TipoConsumo tipoConsumoNafta = new TipoConsumo("Nafta",Unidad.LT,TipoActividad.COMBUSTION_FIJA,TipoAlcance.EMISION_DIRECTA);
        tipoConsumoNafta.setFactorEmision(lt);
        dbConnection.persistir(tipoConsumoNafta);

    }
    */


    public void persistir(Object o) {
        entityManager().getTransaction().begin();
        entityManager().persist(o);
        entityManager().getTransaction().commit();
    }

    public <T> T getById(long id, Class<T> clase ) {
        return entityManager().find(clase,id);
    }

}
