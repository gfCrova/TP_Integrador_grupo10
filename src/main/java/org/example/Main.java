package org.example;

import static org.example.ResultadoEnum.*;

public class Main {

    public static void main(String[] args) {

        Partido partido1 = new Partido();
        Equipo argentina = new Equipo("Argentina", "AFA");
        Equipo brasil = new Equipo("Brasil", "CBF");

        partido1.setEquipoLocal(argentina);
        partido1.setEquipoVisitante(brasil);
        partido1.getEquipoLocal().setCantidadGoles(2);
        partido1.getEquipoVisitante().setCantidadGoles(1);

        Pronostico pronostico1 = new Pronostico(partido1, argentina, GANADOR);
        System.out.println(pronostico1.puntos());

        System.out.println("\n"); // SEPARACIÓN **********************************************

        Partido partido2 = new Partido();
        Equipo uruguay = new Equipo("Uruguay", "AUF");
        Equipo chile = new Equipo("Chile", "FFCh");

        partido2.setEquipoLocal(uruguay);
        partido2.setEquipoVisitante(chile);
        partido2.getEquipoLocal().setCantidadGoles(2);
        partido2.getEquipoVisitante().setCantidadGoles(2);

        Pronostico pronostico2 = new Pronostico(partido2, chile, EMPATE);
        System.out.println(pronostico2.puntos());

        System.out.println("\n"); // SEPARACIÓN **********************************************

        Partido partido3 = new Partido();
        Equipo colombia = new Equipo("Colombia", "FCF");
        Equipo peru = new Equipo("Perú", "FPF");

        partido3.setEquipoLocal(colombia);
        partido3.setEquipoVisitante(peru);
        partido3.getEquipoLocal().setCantidadGoles(0);
        partido3.getEquipoVisitante().setCantidadGoles(1);

        Pronostico pronostico3 = new Pronostico(partido3, peru, PERDEDOR);
        System.out.println(pronostico3.puntos());

    }
}
