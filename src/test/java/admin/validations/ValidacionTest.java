package admin.validations;


import exceptions.PasswordInseguraException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ValidacionTest {
  ValidacionMinCaracteres validarCaracteres = new ValidacionMinCaracteres();
  ValidacionPeoresPasswords validarSiEsBuena = new ValidacionPeoresPasswords();
  String contraseniaCool = "15R25O40J25O15";
  String contraseniaLargaDelArchivo = "pufunga7782";

  @Test
  void unaContraseniaLargaYQueNoEstaEnLaListaEsValida() {
    Assertions.assertDoesNotThrow(() -> validarCaracteres.validar(contraseniaCool));
    Assertions.assertDoesNotThrow(() -> validarSiEsBuena.validar(contraseniaCool));
  }

  @Test
  void unaContraseniaLargaQueEstaEnLaListaNoEsValida() {
    Assertions.assertDoesNotThrow(() -> validarCaracteres.validar(contraseniaLargaDelArchivo));
    Assertions.assertThrows(PasswordInseguraException.class,
        () -> validarSiEsBuena.validar(contraseniaLargaDelArchivo));
  }
}
