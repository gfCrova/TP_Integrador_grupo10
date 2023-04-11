package org.example;

import org.example.Entidades.Partido;
import org.example.Entidades.Persona;
import org.example.Entidades.Ronda;
import org.example.Services.FilesService;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        FilesService files = new FilesService();

        // ***** Salida de Partidos de la Ronda
        ArrayList<Partido> partidosRonda1 = new ArrayList<>();
        Ronda ronda1 = new Ronda("Ronda 1", partidosRonda1);

        files.leerResultados(files.getFileResultados(), partidosRonda1);

        for (int i = 0; i < ronda1.getPartidos().size(); i++) {
            System.out.print("Partido " + (i + 1) + ": " + ronda1.getPartidos().get(i));
        }
        System.out.println("\n");

        // ***** Salida de Resultados de Pronósticos.

        Persona Diego = new Persona("Diego", ronda1, new ArrayList<>());
        Persona Julieta = new Persona("Julieta", ronda1, new ArrayList<>());

        files.leerPronosticos(files.getFilePronosticos(), Diego);
        files.leerPronosticos(files.getFilePronosticos(), Julieta);

        // ***** Salida del Puntaje Total entre los participates.

        int total1 = Diego.getRonda().totalPuntos(Diego.getPronostico(), Diego);
        int total2 = Julieta.getRonda().totalPuntos(Julieta.getPronostico(), Julieta);

        System.out.println( "\n" + "Puntaje Total: \n" +
                Diego.getNombre() + ": " + total1 + "\n" +
                Julieta.getNombre() + ": " + total2);
    }
}
