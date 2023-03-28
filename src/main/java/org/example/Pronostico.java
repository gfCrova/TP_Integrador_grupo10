package org.example;

public class Pronostico {

    private Partido partido;
    private Equipo equipo;
    private ResultadoEnum resultado;

    public Pronostico(Partido partido, Equipo equipo, ResultadoEnum resultado) {
        this.partido = partido;
        this.equipo = equipo;
        this.resultado = resultado;
    }

    public int puntos() {
        if (partido.resultadoPartido(equipo).equals(resultado)) {
            System.out.println("Su pronóstico de '" + this.equipo.getNombre() + " " + resultado + "' fue ACERTADO. Puntos obtenidos: ");
            return 1;
        } else {
            System.out.println("Su pronóstico de '" + this.equipo.getNombre() + " " + resultado + "' NO fue acertado. Puntos obtenidos: ");
            return 0;
        }
    }
}
