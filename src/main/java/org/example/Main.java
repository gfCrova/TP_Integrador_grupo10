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

        ArrayList<Partido> partidosRonda1 = new ArrayList<>();
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

        // Creando Pronósticos y agregándolos al ArrayList de Pronósticos de la Persona
        Persona persona1 = new Persona("Diego", ronda1, new ArrayList<>());
        Pronostico pron1Diego = new Pronostico(partidosRonda1.get(0), partidosRonda1.get(0).getEquipoLocal());
        Pronostico pron2Diego  = new Pronostico(partidosRonda1.get(1), partidosRonda1.get(1).getEquipoLocal());
        Pronostico pron3Diego  = new Pronostico(partidosRonda1.get(2), partidosRonda1.get(2).getEquipoVisitante());
        Pronostico pron4Diego  = new Pronostico(partidosRonda1.get(3), partidosRonda1.get(3).getEquipoVisitante());
        persona1.getPronostico().add(pron1Diego);
        persona1.getPronostico().add(pron2Diego);
        persona1.getPronostico().add(pron3Diego);
        persona1.getPronostico().add(pron4Diego);

        Persona persona2 = new Persona("Julieta", ronda1, new ArrayList<>());
        Pronostico pron1Julieta = new Pronostico(partidosRonda1.get(0), partidosRonda1.get(0).getEquipoLocal());
        Pronostico pron2Julieta  = new Pronostico(partidosRonda1.get(1), partidosRonda1.get(1).getEquipoVisitante());
        Pronostico pron3Julieta  = new Pronostico(partidosRonda1.get(2), partidosRonda1.get(2).getEquipoLocal());
        Pronostico pron4Julieta  = new Pronostico(partidosRonda1.get(3), partidosRonda1.get(3).getEquipoLocal());
        persona2.getPronostico().add(pron1Julieta);
        persona2.getPronostico().add(pron2Julieta);
        persona2.getPronostico().add(pron3Julieta);
        persona2.getPronostico().add(pron4Julieta);

        leerPronostico(pronosticos, persona1);
        leerPronostico(pronosticos, persona2);

        int total1 = persona1.getRonda().totalPuntos(persona1.getPronostico(), persona1);
        int total2 = persona2.getRonda().totalPuntos(persona2.getPronostico(), persona2);
        System.out.println( "\n" + "Puntaje Total: \n" +
                persona1.getNombre() + ": " + total1 + "\n" +
                persona2.getNombre() + ": " + total2);
    }

    public static void leerPronostico(String file, Persona user) {
        try {
            for (String linea : Files.readAllLines(Paths.get(file))) {

                String persona = linea.split(",")[0];
                String equipo1 = linea.split(",")[1];
                String gana1 = linea.split(",")[2];
                String empata = linea.split(",")[3];
                String gana2 = linea.split(",")[4];
                String equipo2 = linea.split(",")[5];

                for (Pronostico pron : user.getPronostico()) {
                    String equipo = pron.getPartido().getEquipoLocal().getNombre();
                    if (persona.equals(user.getNombre())) {
                        if (equipo1.equals(equipo) || equipo2.equals(equipo)) {
                            if (gana1.contains("x") || gana2.contains("x")) {
                                pron.setResultado(GANADOR);
                            } else if (empata.contains("x")) {
                                pron.setResultado(EMPATE);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
