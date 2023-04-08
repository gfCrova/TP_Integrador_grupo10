package org.example;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

import static java.nio.file.Files.*;
import static org.example.ResultadoEnum.*;

public class Main {

    private static final String resultados = "src\\main\\java\\org\\example\\inputFiles\\resultados.csv";
    private static final String pronosticos = "src\\main\\java\\org\\example\\inputFiles\\pronosticos.csv";

    public static void main(String[] args) {
        InputFiles files = new InputFiles();

        // ***** Salida de Partidos de la Ronda
        ArrayList<Partido> partidosRonda1 = new ArrayList<>();
        Ronda ronda1 = new Ronda("Ronda 1", partidosRonda1);

        leerResultados(files.getFileResultados(), partidosRonda1);

        for (int i = 0; i < ronda1.getPartidos().size(); i++) {
            System.out.print("Partido " + (i + 1) + ": " + ronda1.getPartidos().get(i));
        }
        System.out.println("\n");

        // ***** Salida de Resultados de Pronósticos.

        Persona Diego = new Persona("Diego", ronda1, new ArrayList<>());
        Persona Julieta = new Persona("Julieta", ronda1, new ArrayList<>());

        leerPronosticos(files.getFilePronosticos(), Diego);
        leerPronosticos(files.getFilePronosticos(), Julieta);

        // ***** Salida del Puntaje Total entre los participates.

        int total1 = Diego.getRonda().totalPuntos(Diego.getPronostico(), Diego);
        int total2 = Julieta.getRonda().totalPuntos(Julieta.getPronostico(), Julieta);

        System.out.println( "\n" + "Puntaje Total: \n" +
                Diego.getNombre() + ": " + total1 + "\n" +
                Julieta.getNombre() + ": " + total2);
    }

    /* Función que toma un Archivo de resultados y una Lista de partidos vacía.
        Recorre el archivo y va agregando los partidos a la Lista */
    public static void leerResultados(String file, ArrayList<Partido> partidos) {
        try {
            for (String linea : readAllLines(Paths.get(file))) {

                String equipo1 = linea.split(",")[0];
                int goles1 = Integer.parseInt(linea.split(",")[1]);
                int goles2 = Integer.parseInt(linea.split(",")[2]);
                String equipo2 = linea.split(",")[3];

                Equipo local = new Equipo(equipo1);
                Equipo visitante = new Equipo(equipo2);

                Partido partido = new Partido(local, goles1, goles2, visitante);
                partidos.add(partido);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* Función que toma un Archivo de pronósticos y una Persona.
        Se recorre la Ronda de partidos y se crea un pronóstico para cada partido.
         Se recorre el archivo y se verifica si los resultados de los partidos coinciden con los pronósticos dados
            por la Persona y se le asigna un Resultado */
    public static void leerPronosticos(String file, Persona user) {
        try {
            int count = 0;
            while (count < user.getRonda().getPartidos().size()) {

                Equipo equipoLocal = user.getRonda().getPartidos().get(count).getEquipoLocal();
                Equipo equipoVisitante = user.getRonda().getPartidos().get(count).getEquipoVisitante();

                Pronostico pr = new Pronostico(user.getRonda().getPartidos().get(count), equipoLocal);

                for (String linea : readAllLines(Paths.get(file))) {

                    String persona = linea.split(",")[0];
                    String equipo1 = linea.split(",")[1];
                    String gana1 = linea.split(",")[2];
                    String empata = linea.split(",")[3];
                    String gana2 = linea.split(",")[4];
                    String equipo2 = linea.split(",")[5];

                    if (persona.equals(user.getNombre())) {
                        if (equipo1.equals(equipoLocal.getNombre()) || equipo2.equals(equipoVisitante.getNombre())) {
                            if (gana1.contains("x")) {
                                pr.setResultado(GANADOR);
                            } else if (gana2.contains("x")) {
                                pr.setEquipo(equipoVisitante);
                                pr.setResultado(GANADOR);
                            } else if (empata.contains("x")) {
                                pr.setResultado(EMPATE);
                            }
                        }
                    }
                }
                user.getPronostico().add(pr);
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
