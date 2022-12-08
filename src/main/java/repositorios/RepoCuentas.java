package repositorios;

import cuenta.AgenteCuenta;
import cuenta.Cuenta;
import miembro.Miembro;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import organizacion.Organizacion;
import territorio.AgenteTerritorial;

import java.util.List;
import java.util.Optional;


public class RepoCuentas implements WithGlobalEntityManager {
  private static RepoCuentas instance;

  private RepoCuentas() {
  }

  public static RepoCuentas getInstance() {
    if (instance == null) {
      instance = new RepoCuentas();
    }
    return instance;
  }

  public void agregarCuenta(Cuenta cuenta) {
    entityManager().getTransaction().begin();
    entityManager().persist(cuenta);
    entityManager().getTransaction().commit();
  }

  public List<Cuenta> getCuentas() {
    return entityManager().createQuery("From Cuenta").getResultList();
  }

  private Object checkOutOfBounds(List<Object> objects){
    if(objects.isEmpty()){
      return null;
    }
    return objects.get(0);
  }


  public Cuenta accountByUsername(String username) {


    return (Cuenta) checkOutOfBounds(entityManager()
            .createQuery("FROM Cuenta WHERE usuario = :user").setParameter("user",username)
            .getResultList());
  }


  public Miembro obtenerMiembro(Cuenta cuenta) {
    return (Miembro) checkOutOfBounds(entityManager()
            .createQuery("from Miembro where cuenta.id = :cuenta_id").setParameter("cuenta_id", cuenta.getId())
            .getResultList());
  }

  public AgenteTerritorial obtenerAgente(Cuenta cuenta) {
    return (AgenteTerritorial) checkOutOfBounds(entityManager()
        .createQuery("FROM AgenteTerritorial WHERE cuenta.id = :cuenta_id")
        .setParameter("cuenta_id",cuenta.getId())
        .getResultList());
  }

  public AgenteCuenta getCuentaAgente(String user) {
    return (AgenteCuenta) entityManager()
        .createQuery("from Cuenta where Cuenta.usuario = :c").setParameter("c", user)
        .getResultList().get(0);
  }

  public Organizacion obtenerOrganizacion(Cuenta cuenta) {

    Object resultadoQuery = checkOutOfBounds(entityManager()
            .createQuery("from Organizacion where cuenta.id = :cuenta_id").setParameter("cuenta_id", cuenta.getId())
            .getResultList());
    return (Organizacion) resultadoQuery;
  }
}
