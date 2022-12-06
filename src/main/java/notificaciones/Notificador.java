package notificaciones;

import notificaciones.medioNotificacion.MedioNotificador;
import repositorios.RepoOrganizacion;

import java.util.ArrayList;
import java.util.List;

public class Notificador {
  private final List<MedioNotificador> medios = new ArrayList<>();

  public void organizacionesNotifiquen() {
    RepoOrganizacion
        .getInstance()
        .getOrganizaciones()
        .forEach(organizacion -> organizacion.notificarContactos(medios));
  }

  public void agregarMedios(MedioNotificador medio) {
    medios.add(medio);
  }

  public List<MedioNotificador> medios() {
    return medios;
  }

}
