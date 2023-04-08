import org.example.Main;
import org.example.Partido;
import org.example.Persona;
import org.example.Ronda;
import org.example.iFiles;
import org.junit.jupiter.api.Assertions;
import java.util.ArrayList;

public class testPuntajes {
    @org.junit.jupiter.api.Test
    public void testPuntos() {

        iFiles files = new iFiles();
        ArrayList<Partido> partidosRonda1 = new ArrayList<>();
        Ronda ronda1 = new Ronda("Ronda 1", partidosRonda1);
        Persona Diego = new Persona("Diego", ronda1, new ArrayList<>());

        Main.leerResultados(files.getFileResultados(), partidosRonda1);
        Main.leerPronostico(files.getFilePronosticos(), Diego);

        int resultadoEsperado = 3;
        int result = Diego.getRonda().totalPuntos(Diego.getPronostico(), Diego);

        Assertions.assertEquals(result, resultadoEsperado);
    }
}

