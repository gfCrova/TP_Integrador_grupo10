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

        Pronostico pron2 = new Pronostico();
        pron2.setPartido(ronda1.getPartidos().get(1));
        pron2.setEquipo(ronda1.getPartidos().get(1).getEquipoVisitante());
        pron2.setResultado(PERDEDOR);

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
                    System.out.println(pron2.puntos());
                    break;
                } else if (empata.contains("x")) {
                    System.out.println(pron1.puntos());
                    System.out.println(pron2.puntos());
                    break;
                } else if (gana2.contains("x")) {
                    System.out.println(pron1.puntos());
                    System.out.println(pron2.puntos());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
