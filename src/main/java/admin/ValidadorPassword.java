package admin;

import admin.validations.Validacion;
import admin.validations.ValidacionMinCaracteres;
import admin.validations.ValidacionPeoresPasswords;

import java.util.ArrayList;
import java.util.List;


class ValidadorPassword {

  private List<Validacion> validaciones = new ArrayList<>();

  // Hay que modificar los parámetro e ir agregando las validaciones al constructor a medida que van
  // surgiendo.

  public ValidadorPassword() {
    validaciones.add(new ValidacionPeoresPasswords());
    validaciones.add(new ValidacionMinCaracteres());
  }

  /**
   * Valida la contraseña según las validaciones vigentes.
   *
   * @param password
   */
  public void validarPassword(String password) {
    validaciones.forEach(validacion -> validacion.validar(password));
  }

}
