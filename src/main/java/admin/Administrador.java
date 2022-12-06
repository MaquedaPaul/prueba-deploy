package admin;

import repositorios.RepoFactoresEmision;

import javax.persistence.*;

@Entity
@Table(name = "Administrador")
public class Administrador {

  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "usuario")
  private String usuario;

  @Column(name = "password")
  private String password;

  public Administrador() {
  }

  public Administrador(String unUsuario, String unPassword) {
    this.usuario = unUsuario;
    this.password = unPassword;
  }

  public String getUsuario() {
    return usuario;
  }

  public void setUsuario(String usuario) {
    this.usuario = usuario;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  void crearFactorEmision(FactorEmision unFactor) {
    RepoFactoresEmision.getInstance().incorporarFactor(unFactor);
  }

  void modificarFactorDeEmision(FactorEmision unFactor, double nuevoValor) {
    RepoFactoresEmision.getInstance().modificarFactorEmision(unFactor, nuevoValor);
  }
}



