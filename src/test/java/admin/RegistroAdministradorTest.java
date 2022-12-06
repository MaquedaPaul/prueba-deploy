package admin;

import exceptions.PasswordInseguraException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistroAdministradorTest {

  @Test
  void errorPorContraseniaCorta() {
    RegistroAdministrador unRegistro = new RegistroAdministrador();
    unRegistro.especificarUsuario("Jorgito");
    assertEquals(unRegistro.usuario, "Jorgito");
    Assertions.assertThrows(PasswordInseguraException.class, () -> {
      unRegistro.especificarpassword("123");
    });
  }

  @Test
  void seCreaCorrectamenteElAdministrador() {
    RegistroAdministrador unRegistro = new RegistroAdministrador();
    unRegistro.especificarUsuario("Jorgito");
    unRegistro.especificarpassword("UnEjemploSeguro");
    Administrador nuevoAdministrador = unRegistro.construir();
    assertEquals(nuevoAdministrador.getUsuario(), "Jorgito");
    assertEquals(nuevoAdministrador.getPassword(), "UnEjemploSeguro");
  }
}
