package org.example;

public class Pronostico {

    private Partido partido;
    private Equipo equipo;
    private ResultadoEnum resultado;

    public Pronostico(Partido partido, Equipo equipo) {
        this.partido = partido;
        this.equipo = equipo;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public ResultadoEnum getResultado() {
        return resultado;
    }

    public void setResultado(ResultadoEnum resultado) {
        this.resultado = resultado;
    }

    public int puntos(Persona persona) {
        int total = 0;
        if (partido.resultadoPartido(equipo).equals(resultado)) {
            System.out.println(persona.getNombre() + ": Pronóstico '" + this.equipo.getNombre() + " " + resultado + "' ACERTADO. Puntos: +" + 1);
            return total += 1;
        } else {
            System.out.println(persona.getNombre() + ": Pronóstico '" + this.equipo.getNombre() + " " + resultado + "' NO acertado. Puntos: " + 0);
            return total += 0;
        }
    }
}
