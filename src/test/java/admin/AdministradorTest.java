package admin;

import global.Unidad;
import org.junit.jupiter.api.Test;
import repositorios.RepoFactoresEmision;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdministradorTest {


  @Test
  void agregarUnFactorDeEmision() {
    Administrador unAdministrador = new Administrador("ejemplo", "esteEsUnEjemplo");
    FactorEmision nuevoFactor = new FactorEmision(300, Unidad.LTS);
    unAdministrador.crearFactorEmision(nuevoFactor);

    assertEquals(RepoFactoresEmision.getInstance().factoresTotales(), 1);
  }

  @Test
  void modificarFactorDeEmision() {
    Administrador unAdministrador = new Administrador("ejemplo", "esteEsUnEjemplo");
    FactorEmision unFactor = new FactorEmision(300, Unidad.LTS);
    RepoFactoresEmision repo = RepoFactoresEmision.getInstance();
    repo.incorporarFactor(unFactor);
    unAdministrador.modificarFactorDeEmision(unFactor, 450);
    //TODO
    //assertEquals(RepoFactoresEmision.getInstance().getFactoresEmision().get(0).getValor(), 450);
  }
}
