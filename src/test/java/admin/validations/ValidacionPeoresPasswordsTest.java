package admin.validations;


import exceptions.PasswordInseguraException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ValidacionPeoresPasswordsTest {
  String passwordPeor = "password";
  String passwordNoSeEncuentraEnLista = "lucasBk";
  ValidacionPeoresPasswords validadorPeoresPasswords = new ValidacionPeoresPasswords();

  @Test
  void unaPasswordQueSeEncuentraEnLaListaPeoresPassDebeFallar() {
    Assertions.assertThrows(PasswordInseguraException.class, () -> {
      validadorPeoresPasswords.validar(passwordPeor);
    });
  }

  @Test
  void unaPasswordQueNoSeEncuentraEnLaListaPeoresPassDebeSerValida() {
    Assertions.assertDoesNotThrow(() -> {
      validadorPeoresPasswords.validar(passwordNoSeEncuentraEnLista);
    });
  }
}
