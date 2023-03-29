package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.example.ResultadoEnum.*;

public class Main {

    public static void main(String[] args) {

        ArrayList<Partido> partidosRonda1 = new ArrayList<Partido>();
        Ronda ronda1 = new Ronda("Ronda 1", partidosRonda1);

        try {
            String resultados = "src\\main\\java\\org\\example\\csv\\resultados.csv";

            for (String linea : Files.readAllLines(Paths.get(resultados))) {

                String equipo1 = linea.split(",")[0];
                int goles1 = Integer.parseInt(linea.split(",")[1]);
                int goles2 = Integer.parseInt(linea.split(",")[2]);
                String equipo2 = linea.split(",")[3];

                Equipo local = new Equipo(equipo1);
                Equipo visitante = new Equipo(equipo2);

                Partido partido = new Partido(local, goles1, goles2, visitante);
                partidosRonda1.add(partido);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(ronda1.getPartidos());

        //////////////////////////////////////////////////////

        ArrayList<Pronostico> listaPronosticos = new ArrayList<>();
        Persona persona1 = new Persona("Diego", ronda1, listaPronosticos);

        Pronostico pronostico1 = new Pronostico(ronda1.getPartidos().get(0), ronda1.getPartidos().get(0).getEquipoLocal());
        Pronostico pronostico2 = new Pronostico(ronda1.getPartidos().get(1), ronda1.getPartidos().get(1).getEquipoLocal());
        Pronostico pronostico3 = new Pronostico(ronda1.getPartidos().get(2), ronda1.getPartidos().get(2).getEquipoLocal());

        persona1.getPronostico().add(pronostico1);
        persona1.getPronostico().add(pronostico2);
        persona1.getPronostico().add(pronostico3);

        try {
            String pronosticos = "src\\main\\java\\org\\example\\csv\\pronosticos.csv";

            for (String linea : Files.readAllLines(Paths.get(pronosticos))) {

                String equipo1 = linea.split(",")[0];
                String gana1 = linea.split(",")[1];
                String empata = linea.split(",")[2];
                String gana2 = linea.split(",")[3];
                String equipo2 = linea.split(",")[4];

                for (Pronostico p : listaPronosticos) {
                    if (equipo1.equals(p.getPartido().getEquipoLocal().getNombre())) {
                        if (gana1.contains("x")) {
                            p.setResultado(GANADOR);
                        } else if (empata.contains("x")) {
                            p.setResultado(EMPATE);
                        } else {
                            p.setResultado(PERDEDOR);
                        }
                    } else if (equipo2.equals(p.getPartido().getEquipoVisitante().getNombre())) {
                        if (gana2.contains("x")) {
                            p.setResultado(GANADOR);
                        } else if (empata.contains("x")) {
                            p.setResultado(EMPATE);
                        } else {
                            p.setResultado(PERDEDOR);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(persona1.getNombre() + ": Puntaje Total = " + persona1.getRonda().totalPuntos(listaPronosticos));

    }
}
