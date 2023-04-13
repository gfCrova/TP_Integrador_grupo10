package org.example.Entidades;

import org.example.Enums.ResultadoEnum;

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
            } else if (getGolesLocal() < getGolesVisitante()) {
                return ResultadoEnum.PERDEDOR;
            }
        } else if (equipo == equipoVisitante) {
            if (getGolesVisitante() > getGolesLocal()) {
                return ResultadoEnum.GANADOR;
            } else if (getGolesVisitante() < getGolesLocal()) {
                return ResultadoEnum.PERDEDOR;
            }
        }
        return ResultadoEnum.EMPATE;
    }

    @Override
    public String toString() {
        return "\t" + equipoLocal + " " + golesLocal + " - " + golesVisitante + " " + equipoVisitante + "\n";
    }
}
