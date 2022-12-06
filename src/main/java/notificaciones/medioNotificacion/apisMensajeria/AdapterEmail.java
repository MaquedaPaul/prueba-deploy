package notificaciones.medioNotificacion.apisMensajeria;

import notificaciones.Contacto;
import notificaciones.medioNotificacion.MedioNotificador;
import organizacion.Organizacion;

import java.time.LocalDate;
import java.util.List;

public class AdapterEmail implements MedioNotificador {

  private String asunto;
  private String cuerpo;
  private String url;

  @Override
  public void enviarATodos(List<Contacto> contactos, Organizacion organizacion) {
    contactos.forEach(contacto -> this.enviarA(contacto, organizacion));
  }

  @Override
  public void enviarA(Contacto contacto, Organizacion org) {
    //APIMail.mailTo(...);
  }

  @Override
  public String mensajePersonalizadoPara(Contacto contacto, Organizacion org) {
    String mensajePersonalizado = this.cuerpo
        .replace("*NOMBRE_CONTACTO*", contacto.getNombreContacto());
    mensajePersonalizado = mensajePersonalizado
        .replace("*ORGANIZACION*", org.getRazonSocial());
    mensajePersonalizado = mensajePersonalizado
        .replace("*MES*", LocalDate.now().getMonth().toString());
    mensajePersonalizado = mensajePersonalizado
        .replace("*URL*", this.url);
    return mensajePersonalizado;
  }

  @Override
  public void setAsunto(String asunto) {
    this.asunto = asunto;
  }

  @Override
  public String getUrl() {
    return url;
  }

  @Override
  public void setUrl(String url) {
    this.url = url;
  }

  @Override
  public String getMensaje() {
    return cuerpo;
  }

  @Override
  public void setMensaje(String cuerpo) {
    this.cuerpo = cuerpo;
  }

  @Override
  public String getAsunto() {
    return asunto.replace("*MES/AÑO*", LocalDate.now()
        .getMonth() + " " + LocalDate.now().getYear()).toUpperCase();
  }

}
