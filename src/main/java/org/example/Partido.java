package org.example;

public class Partido {

    private Equipo equipoLocal;
    private Equipo equipoVisitante;
    private int golesLocal;
    private int golesVisitante;

    public Partido() {
    }

    public Partido(Equipo equipoLocal, int golesLocal, int golesVisitante, Equipo equipoVisitante) {
        this.equipoLocal = equipoLocal;
        this.golesLocal = golesLocal;
        this.golesVisitante = golesVisitante;
        this.equipoVisitante = equipoVisitante;
    }

    public Equipo getEquipoLocal() {
        return equipoLocal;
    }

    public void setEquipoLocal(Equipo local) {
        this.equipoLocal = local;
    }

    public Equipo getEquipoVisitante() {
        return equipoVisitante;
    }

    public void setEquipoVisitante(Equipo visitante) {
        this.equipoVisitante = visitante;
    }

    public int getGolesLocal() {
        return golesLocal;
    }

    public void setGolesLocal(int golesLocal) {
        this.golesLocal = golesLocal;
    }

    public int getGolesVisitante() {
        return golesVisitante;
    }

    public void setGolesVisitante(int golesVisitante) {
        this.golesVisitante = golesVisitante;
    }

    public ResultadoEnum resultadoPartido(Equipo equipo) {
        if (equipo == equipoLocal) {
            if (getGolesLocal() > getGolesVisitante()) {
                return ResultadoEnum.GANADOR;
            } else if  (getGolesLocal() == getGolesVisitante()) {
                return ResultadoEnum.EMPATE;
            } else {
                return ResultadoEnum.PERDEDOR;
            }
        } else if (equipo == equipoVisitante) {
            if (getGolesVisitante() > getGolesLocal()) {
                return ResultadoEnum.GANADOR;
            } else if (getGolesLocal() == getGolesVisitante()) {
                return ResultadoEnum.EMPATE;
            } else {
                return ResultadoEnum.PERDEDOR;
            }
        }
        return ResultadoEnum.PERDEDOR;
    }

    @Override
    public String toString() {
        return "\t" + equipoLocal + " " + golesLocal + " - " + golesVisitante + " " + equipoVisitante + "\n";
    }
}
