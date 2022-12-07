package server;

import controllers.*;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {
  public static void init() {
    HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
    RaizController raizController = new RaizController();
    HomeController homeController = new HomeController();
    RecomendacionController recomendacionController = new RecomendacionController();

    SignInController signInController = new SignInController();
    SignOutController signOutController = new SignOutController();

    MiembroController miembroController = new MiembroController();
    OrganizacionController organizacionController = new OrganizacionController();
    AgenteController agenteController = new AgenteController();

    Spark.staticFiles.location("static");


    Spark.before(((request, response) -> {
      PerThreadEntityManagers.getEntityManager().getTransaction().begin();
      PerThreadEntityManagers.getEntityManager().flush();
      PerThreadEntityManagers.getEntityManager().close();
    }));

    Spark.before(Validador::validarAcceso);

    Spark.after(
        (req, res) ->
        {
          if (PerThreadEntityManagers.getEntityManager().getTransaction().isActive()) {
            PerThreadEntityManagers.getEntityManager().getTransaction().commit();
          }
        }
    );

    //COMUNES PARA TODOS
    Spark.get("/", raizController::getPage, engine);
    Spark.get("/recomendaciones", recomendacionController::getRecomendaciones, engine);
    Spark.get("/home",homeController::getHome, engine);
    Spark.get("/signin",signInController::getSignIn, engine);
    Spark.post("/signin", signInController::logIn, engine);
    Spark.post("/signout", signOutController::logOut, engine);

    //MIEMBRO
    Spark.get("/home/trayectos", miembroController::getTrayectos, engine);
    Spark.get("/home/trayectos/registro", miembroController::getRegistrarTrayecto, engine);
    Spark.get("/home/trayectos/registro/tramo-nuevo", miembroController::getTrayectoNuevo, engine);
    Spark.post("/home/trayectos/registro/tramo-nuevo", miembroController::cargarTramo, engine);
    Spark.get("/home/trayectos/compartir", miembroController::getRegistro, engine);
    Spark.post("/home/trayectos/compartir", miembroController::getRegistro, engine);
    Spark.post("/home/trayectos/registro/eliminar", miembroController::eliminarTramo, engine);
    Spark.post("/home/trayectos/registro/cancelar", miembroController::cancelarTrayecto, engine);
    Spark.post("/home/trayectos/registro/crear", miembroController::crearTrayecto, engine);
    Spark.get("/home/vinculacion", miembroController::getVinculacion, engine);
    Spark.post("/home/vinculacion", miembroController::pedirVinculacion, engine);

    //ORGANIZACION
    Spark.get("/home/vinculaciones", organizacionController::getVinculaciones, engine);
    Spark.post("/home/vinculaciones/:id/aceptar", organizacionController::aceptarVinculacion,engine);
    Spark.post("/home/vinculaciones/:id/rechazar", organizacionController::rechazarVinculacion,engine);
    Spark.post("/home/vinculaciones", miembroController::pedirVinculacion, engine);
    Spark.get("/home/mediciones", organizacionController::getMediciones, engine);
    Spark.get("/home/mediciones/perse", organizacionController::getMedicionesPerse, engine);
    Spark.post("/home/mediciones/perse/creado",organizacionController::crearMedicion,engine);
    Spark.get("/home/mediciones/archivo", organizacionController::getMedicionesArchivo, engine);
    Spark.post("/home/mediciones/archivo", organizacionController::subirCSVs, engine);
    Spark.get("/home/calculadora-hc", organizacionController::getCalculadoraHc, engine);
    Spark.get("/home/calculadora-hc/hc-total", organizacionController::getHcTotal, engine);
    Spark.get("/home/calculadora-hc/impacto-de-miembro/buscador", organizacionController::getImpactoMiembroBuscar, engine);
    Spark.get("/home/calculadora-hc/impacto-de-miembro", organizacionController::getImpactoMiembro, engine);
    Spark.get("/home/calculadora-hc/impacto-de-miembro/:nombreApellido", organizacionController::getImpactoMiembroConNombreYApellido, engine);
    Spark.get("/home/calculadora-hc/indicador-hc-sector/buscador", organizacionController::getIndicadorHcSectorBuscar, engine);
    Spark.get("/home/calculadora-hc/indicador-hc-sector", organizacionController::getIndicadorHcSector, engine);
    Spark.get("/home/calculadora-hc/indicador-hc-sector/:nombre", organizacionController::getIndicadorHcSectorConNombre, engine);

    //AGENTE
    Spark.get("/home/composicion-hc",agenteController::getComposicionHc,engine);
    Spark.get("/home/composicion-hc/grafico",agenteController::getComposicionHcGrafico,engine);
    Spark.get("/home/evolucion-hc",agenteController::getEvolucionHc,engine);
    Spark.get("/home/evolucion-hc/grafico",agenteController::getEvolucionHcGrafico,engine);
    Spark.get("home/organizaciones",agenteController::getOrganizaciones,engine);
    Spark.get("home/hc-total",agenteController::getHcTotal,engine);
    Spark.get("/home/organizaciones/hc-tipo-organizacion/:tipo",agenteController::getHcTipoOrg,engine);
    Spark.get("/home/organizaciones/:id/evolucion-hc/consulta",agenteController::getEvolucionHcOrg,engine);
    Spark.get("/home/organizaciones/:id/evolucion-hc/grafico",agenteController::getEvolucionHcOrgGrafico,engine);
    Spark.get("/home/organizaciones/:id/composicion-hc/consulta",agenteController::getComposicionHcOrg,engine);
    Spark.get("/home/organizaciones/:id/composicion-hc/grafico",agenteController::getComposicionHcOrgGrafico,engine);
  }
}
