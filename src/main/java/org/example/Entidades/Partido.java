package org.example.Entidades;

import org.example.Enums.ResultadoEnum;

public class Partido {

    private Equipo equipoLocal;
    private Equipo equipoVisitante;
    private int golesLocal;
    private int golesVisitante;

    private int id, golesEquipo1, golesEquipo2, rondaNumero;
    private String equipo1, equipo2;


    public Partido() {
    }

    public Partido(Equipo equipoLocal, int golesLocal, int golesVisitante, Equipo equipoVisitante) {
        this.equipoLocal = equipoLocal;
        this.golesLocal = golesLocal;
        this.golesVisitante = golesVisitante;
        this.equipoVisitante = equipoVisitante;
    }

    public Partido(int id, Equipo equipo1, int golesEquipo1, int golesEquipo2, Equipo equipo2, int rondaNumero) {
        this.id = id;
        this.equipoLocal = equipo1;
        this.golesLocal = golesEquipo1;
        this.golesVisitante = golesEquipo2;
        this.equipoVisitante = equipo2;
        this.rondaNumero = rondaNumero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGolesEquipo1() {
        return golesEquipo1;
    }

    public void setGolesEquipo1(int golesEquipo1) {
        this.golesEquipo1 = golesEquipo1;
    }

    public int getGolesEquipo2() {
        return golesEquipo2;
    }

    public void setGolesEquipo2(int golesEquipo2) {
        this.golesEquipo2 = golesEquipo2;
    }

    public String getEquipo1() {
        return equipo1;
    }

    public void setEquipo1(String equipo1) {
        this.equipo1 = equipo1;
    }

    public String getEquipo2() {
        return equipo2;
    }

    public void setEquipo2(String equipo2) {
        this.equipo2 = equipo2;
    }

    public int getRondaNumero() {
        return rondaNumero;
    }

    public void setRondaNumero(int rondaNumero) {
        this.rondaNumero = rondaNumero;
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
