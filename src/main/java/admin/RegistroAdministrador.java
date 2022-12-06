package admin;

import static java.util.Objects.requireNonNull;

public class RegistroAdministrador {
  String usuario;
  String password;

  Administrador construir() {
    this.validar();
    return new Administrador(usuario, password);
  }

  public void especificarpassword(String password) {
    ValidadorPassword unValidador = new ValidadorPassword();
    unValidador.validarPassword(password);
    requireNonNull(password);
    this.password = password;
  }

  public void especificarUsuario(String usuario) {
    requireNonNull(usuario);
    this.usuario = usuario;
  }

  private void validar() {
    requireNonNull(password);
    requireNonNull(usuario);
  }
}

