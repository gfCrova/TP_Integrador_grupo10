import org.example.*;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;

public class testPuntajes {

    InputFiles files = new InputFiles();
    ArrayList<Partido> partidos = new ArrayList<Partido>();
    Ronda ronda = new Ronda("Ronda", partidos);
    Persona Carlos = new Persona("Carlos", ronda, new ArrayList<>());

    @org.junit.jupiter.api.Test
    public void testPuntosRonda1() {

        Main.leerResultados(files.getFileTestRonda1(), partidos);
        Main.leerPronosticos(files.getFileTestPron1(), Carlos);

        int result = Carlos.getRonda().totalPuntos(Carlos.getPronostico(), Carlos);
        int resultadoEsperado = 6;

        Assertions.assertEquals(result, resultadoEsperado);
        System.out.println("Total: " + result + ". Resultado Esperado: " + resultadoEsperado + "\n");
    }

    @org.junit.jupiter.api.Test
    public void testPuntosRonda2() {

        Main.leerResultados(files.getFileTestRonda2(), partidos);
        Main.leerPronosticos(files.getFileTestPron2(), Carlos);

        int result = Carlos.getRonda().totalPuntos(Carlos.getPronostico(), Carlos);
        int resultadoEsperado = 7;

        Assertions.assertEquals(result, resultadoEsperado);
        System.out.println("Total: " + result + ". Resultado Esperado: " + resultadoEsperado + "\n");
    }
}
