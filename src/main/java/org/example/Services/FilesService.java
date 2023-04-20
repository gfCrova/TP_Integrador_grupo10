package org.example.Services;

import org.example.Entidades.Equipo;
import org.example.Entidades.Partido;
import org.example.Entidades.Persona;
import org.example.Entidades.Pronostico;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

import static java.nio.file.Files.readAllLines;
import static org.example.Enums.ResultadoEnum.*;

public class FilesService {

    public FilesService() {
    }

    public String getFileResultados() { 
        return "src\\main\\resources\\resultados.csv";
    }

    public String getFilePronosticos() {
        return "src\\main\\resources\\pronosticos.csv";
    }

    public String getFileTestRonda1() { 
        return "src\\test\\testFiles\\ronda1.csv";
    }

    public String getFileTestRonda2() { 
        return "src\\test\\testFiles\\ronda2.csv";
    }

    public String getFileTestPron1() { return "src\\test\\testFiles\\pron1.csv";}

    public String getFileTestPron2() { 
        return "src\\test\\testFiles\\pron2.csv";
    }


    /* Función que toma un Archivo de resultados y una Lista de partidos vacía.
        Recorre el archivo y va agregando los partidos a la Lista */
    public void leerResultados(String file, ArrayList<Partido> partidos) {
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
    public void leerPronosticos(String file, Persona user) {
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

                // Mostrar resultados de pronósticos en pantalla
                if(pr.puntos(user) == 1) {
                    System.out.println(user.getNombre() + " - Pronóstico Partido " + (count + 1) + ": ACERTADO!. Puntos: +" + pr.puntos(user));
                } else {
                    System.out.println(user.getNombre() + " - Pronóstico Partido " + (count + 1) + ": NO acertado. Puntos: " + pr.puntos(user));
                }
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
