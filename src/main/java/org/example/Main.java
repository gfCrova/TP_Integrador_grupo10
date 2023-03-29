package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.example.ResultadoEnum.*;

public class Main {

    public static void main(String[] args) {

        /*Partido partido1 = new Partido();
        Equipo argentina = new Equipo("Argentina", "AFA");
        Equipo brasil = new Equipo("Brasil", "CBF");

        partido1.setEquipoLocal(argentina);
        partido1.setEquipoVisitante(brasil);
        partido1.setGolesLocal(2);
        partido1.setGolesVisitante(1);

        Pronostico pronostico1 = new Pronostico(partido1, argentina, GANADOR);
        System.out.println(pronostico1.puntos());

        System.out.println("\n"); // SEPARACIÓN **********************************************

        Partido partido2 = new Partido();
        Equipo uruguay = new Equipo("Uruguay", "AUF");
        Equipo chile = new Equipo("Chile", "FFCh");

        partido2.setEquipoLocal(uruguay);
        partido2.setEquipoVisitante(chile);
        partido2.setGolesLocal(2);
        partido2.setGolesVisitante(2);

        Pronostico pronostico2 = new Pronostico(partido2, chile, EMPATE);
        System.out.println(pronostico2.puntos());

        System.out.println("\n"); // SEPARACIÓN **********************************************

        Partido partido3 = new Partido();
        Equipo colombia = new Equipo("Colombia", "FCF");
        Equipo peru = new Equipo("Perú", "FPF");

        partido3.setEquipoLocal(colombia);
        partido3.setEquipoVisitante(peru);
        partido3.setGolesLocal(0);
        partido3.setGolesVisitante(1);

        Pronostico pronostico3 = new Pronostico(partido3, peru, PERDEDOR);
        System.out.println(pronostico3.puntos());
        */

        //////////////////////////////////////////////////

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

        Pronostico pron1 = new Pronostico();
        pron1.setPartido(ronda1.getPartidos().get(0));
        pron1.setEquipo(ronda1.getPartidos().get(0).getEquipoLocal());
        pron1.setResultado(GANADOR);

        try {

            String pronosticos = "src\\main\\java\\org\\example\\csv\\pronosticos.csv";

            for (String linea : Files.readAllLines(Paths.get(pronosticos))) {

                String equipo1 = linea.split(",")[0];
                String gana1 = linea.split(",")[1];
                String empata = linea.split(",")[2];
                String gana2 = linea.split(",")[3];
                String equipo2 = linea.split(",")[4];

                if (gana1.contains("x")){
                    System.out.println(pron1.puntos());
                    break;
                } else if (empata.contains("x")) {
                    System.out.println(pron1.puntos());
                    break;
                } else if (gana2.contains("x")) {
                    System.out.println(pron1.puntos());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
