package notificaciones;


import notificaciones.medioNotificacion.apisMensajeria.AdapterEmail;
import notificaciones.medioNotificacion.apisMensajeria.AdapterWhatsapp;
//Aqui se desarrollaría el CRON
//Cron cada cierto tiempo se encargaría de avisarle al notificador que envie guias,
// y este último se encargaría justamente de eso, enviar guias.

public class TareaProgramada {

  public static void main(String[] args) {
    //inicializo medios noti y los guardo
    AdapterEmail mail = new AdapterEmail();
    AdapterWhatsapp whatsapp = new AdapterWhatsapp();
    Notificador notificador = new Notificador();
    notificador.agregarMedios(mail);
    notificador.agregarMedios(whatsapp);
    notificador.organizacionesNotifiquen();
  }
}
