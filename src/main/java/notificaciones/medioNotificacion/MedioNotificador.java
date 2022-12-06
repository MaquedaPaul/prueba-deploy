package notificaciones.medioNotificacion;

import notificaciones.Contacto;
import organizacion.Organizacion;

import java.util.List;

public interface MedioNotificador {


  void enviarATodos(List<Contacto> contactos, Organizacion organizacion);

  void enviarA(Contacto contacto, Organizacion organizacion);

  String getUrl();

  void setUrl(String url);

  void setMensaje(String mensaje);

  String getMensaje();

  String mensajePersonalizadoPara(Contacto contacto, Organizacion org);

  void setAsunto(String asunto);

  String getAsunto();
}
