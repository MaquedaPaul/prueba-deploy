package admin.validations;


import exceptions.PasswordInseguraException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidacionMinCaracteresTest {
  String passwordLarga = "bansdhilasbdlhasbda";
  String passwordJusta = "cocacola";
  String passwordCorta = "123";

  ValidacionMinCaracteres minimosCaracteres = new ValidacionMinCaracteres();

  @Test
  public void unaPasswordCortaDeberiaNoValidarse() {
    assertThrows(PasswordInseguraException.class, () -> {
      minimosCaracteres.validar(passwordCorta);
    });
  }

  @Test
  public void unaPasswordDe8CaracteresDeberiaValidarse() {
    Assertions.assertDoesNotThrow(() -> {
      minimosCaracteres.validar(passwordJusta);
    });
  }

  @Test
  public void unaPasswordDeMasDe8DeberiaValidarse() {
    Assertions.assertDoesNotThrow(() -> {
      minimosCaracteres.validar(passwordLarga);
    });
  }
}
