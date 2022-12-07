package lectorcsv.medicionesprueba;

import lectorcsv.ValidadorDeCabeceras;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class ValidadorDeCabecerasTest {

  List<String> cabeceraEsperada = Arrays.asList("HOLA,SOY,UNA,CABECERA".split(","));

  @Test
  public void unaCabeceraConColumnasIncorrectasNoEsValida() {

    ValidadorDeCabeceras validador = new ValidadorDeCabeceras(cabeceraEsperada);

    Assertions.assertFalse(validador.validar(Arrays.asList("HOLA,NO,SOY,VALIDO".split(","))));
  }

  @Test
  public void unaCabeceraConColumnasCorrectasEsValida() {

    ValidadorDeCabeceras validador = new ValidadorDeCabeceras(cabeceraEsperada);

    Assertions.assertTrue(validador.validar(Arrays.asList("HOLA,SOY,UNA,CABECERA".split(","))));
  }
}
