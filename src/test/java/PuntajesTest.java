import org.example.Entidades.Partido;
import org.example.Entidades.Persona;
import org.example.Entidades.Ronda;
import org.example.Services.FilesService;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;

public class PuntajesTest {

    FilesService files = new FilesService();
    ArrayList<Partido> partidos = new ArrayList<Partido>();
    Ronda ronda = new Ronda(1, partidos);
    Persona Carlos = new Persona(1, "Carlos", ronda, new ArrayList<>());

    @org.junit.jupiter.api.Test
    public void testPuntosRonda1() {

        files.leerResultados(files.getFileTestRonda1(), partidos);
        files.leerPronosticos(files.getFileTestPron1(), Carlos);

        int result = Carlos.getRonda().totalPuntos(Carlos.getPronostico(), Carlos);
        int resultadoEsperado = 6;

        Assertions.assertEquals(result, resultadoEsperado);
        System.out.println("Total: " + result + ". Resultado Esperado: " + resultadoEsperado + "\n");
    }

    @org.junit.jupiter.api.Test
    public void testPuntosRonda2() {

        files.leerResultados(files.getFileTestRonda2(), partidos);
        files.leerPronosticos(files.getFileTestPron2(), Carlos);

        int result = Carlos.getRonda().totalPuntos(Carlos.getPronostico(), Carlos);
        int resultadoEsperado = 7;

        Assertions.assertEquals(result, resultadoEsperado);
        System.out.println("Total: " + result + ". Resultado Esperado: " + resultadoEsperado + "\n");
    }
}
