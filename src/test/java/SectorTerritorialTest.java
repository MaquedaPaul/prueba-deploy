import org.junit.jupiter.api.Test;
import organizacion.Organizacion;
import organizacion.TipoOrganizacion;
import organizacion.periodo.Periodo;
import organizacion.periodo.PeriodoMensual;
import repositorios.RepoSectorTerritorial;
import territorio.SectorTerritorial;
import territorio.TipoSectorTerritorial;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;


public class SectorTerritorialTest {

//  SectorTerritorial sector = RepoSectorTerritorial.getInstance().getSectorById(1);

  @Test
  public void test() {
    Periodo pe = new PeriodoMensual(LocalDate.now());

  }
}
