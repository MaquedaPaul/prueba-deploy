package notificaciones;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Contacto {

  @Id
  @GeneratedValue
  private Long id;

  private String nombreContacto;
  private String mail;
  private String nroCelular;

  public Contacto() {
  }

  public Long getId() {
    return id;
  }

  public Contacto(
      String nombreContacto,
      String mail,
      String nroCelular) {
    this.mail = mail;
    this.nroCelular = nroCelular;
    this.nombreContacto = nombreContacto;
  }

  public String getNroCelular() {
    return nroCelular;
  }

  public void setNroCelular(String nroCelular) {
    this.nroCelular = nroCelular;
  }

  public String getMail() {
    return mail;
  }

  public void setMail(String mail) {
    this.mail = mail;
  }

  public String getNombreContacto() {
    return nombreContacto;
  }

}
