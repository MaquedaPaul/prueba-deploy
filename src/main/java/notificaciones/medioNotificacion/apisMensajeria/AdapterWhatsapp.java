package notificaciones.medioNotificacion.apisMensajeria;

import notificaciones.Contacto;
import notificaciones.medioNotificacion.MedioNotificador;
import organizacion.Organizacion;

import java.time.LocalDate;
import java.util.List;

public class AdapterWhatsapp implements MedioNotificador {

  private String mensajePlantilla;
  private String url;
  private String asunto;
  //private ApiWhatsapp apiWhatsapp;

  @Override
  public void enviarATodos(List<Contacto> contactos, Organizacion org) {
    contactos.forEach(contacto -> this.enviarA(contacto, org));
  }

  @Override
  public void enviarA(Contacto contacto, Organizacion org) {
    ////apiWhatsapp.enviarA(contacto, mensaje);
  }

  @Override
  public String getUrl() {
    return url;
  }

  @Override
  public void setMensaje(String mensaje) {
    this.mensajePlantilla = mensaje;
  }

  @Override
  public String getMensaje() {
    return mensajePlantilla;
  }

  @Override
  public void setUrl(String url) {
    this.url = url;
  }

  @Override
  public String mensajePersonalizadoPara(Contacto contacto, Organizacion org) {
    String mensajePersonalizado = this.mensajePlantilla
        .replace("*NOMBRE_CONTACTO*", contacto.getNombreContacto());
    mensajePersonalizado = mensajePersonalizado
        .replace("*ORGANIZACION*", org.getRazonSocial());
    mensajePersonalizado = mensajePersonalizado
        .replace("*MES*", LocalDate.now().getMonth().toString());
    mensajePersonalizado = mensajePersonalizado
        .replace("*URL*", this.url);
    return this.getAsunto() + "\n" + mensajePersonalizado;
  }

  @Override
  public void setAsunto(String asunto) {
    this.asunto = asunto;
  }

  @Override
  public String getAsunto() {
    return asunto.replace("*MES/AÑO*", LocalDate.now()
        .getMonth() + " " + LocalDate.now().getYear()).toUpperCase();
  }
}
