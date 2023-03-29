package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.example.ResultadoEnum.*;

public class Main {

    private static final String resultados = "src\\main\\java\\org\\example\\csv\\resultados.csv";
    private static final String pronosticos = "src\\main\\java\\org\\example\\csv\\pronosticos.csv";

    public static void main(String[] args) {

        ArrayList<Partido> partidosRonda1 = new ArrayList<Partido>();
        Ronda ronda1 = new Ronda("Ronda 1", partidosRonda1);

        try {
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

        // Obteniendo los partidos de la Ronda 1
        Partido partido1 = ronda1.getPartidos().get(0); // Arg vs Bra
        Partido partido2 = ronda1.getPartidos().get(1); // Uru vs Chi
        Partido partido3 = ronda1.getPartidos().get(2); // Col vs Per

        // Creando Pronosticos de la ronda 1 y agregandolos al ArrayList de Pronosticos de la Persona
        Pronostico pronostico1 = new Pronostico(partido1, partido1.getEquipoLocal());
        Pronostico pronostico2 = new Pronostico(partido2, partido2.getEquipoLocal());
        Pronostico pronostico3 = new Pronostico(partido3, partido3.getEquipoLocal());

        ArrayList<Pronostico> listaPronosticos = new ArrayList<>();
        Persona persona1 = new Persona("Diego", ronda1, listaPronosticos);

        persona1.getPronostico().add(pronostico1);
        persona1.getPronostico().add(pronostico2);
        persona1.getPronostico().add(pronostico3);

        try {
            for (String linea : Files.readAllLines(Paths.get(pronosticos))) {

                String equipo1 = linea.split(",")[0];
                String gana1 = linea.split(",")[1];
                String empata = linea.split(",")[2];
                String gana2 = linea.split(",")[3];
                String equipo2 = linea.split(",")[4];

                for (Pronostico pron : listaPronosticos) {
                    if (equipo1.equals(pron.getPartido().getEquipoLocal().getNombre())) {
                        if (gana1.contains("x")) {
                            pron.setResultado(GANADOR);
                        } else if (empata.contains("x")) {
                            pron.setResultado(EMPATE);
                        } else {
                            pron.setResultado(PERDEDOR);
                        }
                    } else if (equipo2.equals(pron.getPartido().getEquipoVisitante().getNombre())) {
                        if (gana2.contains("x")) {
                            pron.setResultado(GANADOR);
                        } else if (empata.contains("x")) {
                            pron.setResultado(EMPATE);
                        } else {
                            pron.setResultado(PERDEDOR);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Mostrando el puntaje total de los pronosticos dados por una persona
        int puntajeTotal = persona1.getRonda().totalPuntos(listaPronosticos);
        System.out.println(persona1.getNombre() + ": Puntaje Total = " + puntajeTotal);

    }
}
