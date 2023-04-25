package org.example.Entidades;

import org.example.Enums.ResultadoEnum;
import org.example.Services.ConfigService;

public class Pronostico {
    private Integer idPronostico, idPersona;
    private String persona, equipo1, gana1, empate, gana2, equipo2;

    private Partido partido;
    private Equipo equipo;
    private ResultadoEnum resultado;
    private ResultadoEnum comprobar;

    public Pronostico(Partido partido, Equipo equipo) {
        this.partido = partido;
        this.equipo = equipo;
    }

    public Pronostico(int id, String persona, String equipo1, String gana1, String empate, String gana2, String equipo2, int idPersona) {
        this.idPronostico = id;
        this.persona = persona;
        this.equipo1 = equipo1;
        this.gana1 = gana1;
        this.empate = empate;
        this.gana2 = gana2;
        this.equipo2 = equipo2;
        this.idPersona = idPersona;
    }

    public Integer getIdPronostico() {
        return idPronostico;
    }

    public void setIdPronostico(Integer idPronostico) {
        this.idPronostico = idPronostico;
    }

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public String getEquipo1() {
        return equipo1;
    }

    public void setEquipo1(String equipo1) {
        this.equipo1 = equipo1;
    }

    public String getGana1() {
        return gana1;
    }

    public void setGana1(String gana1) {
        this.gana1 = gana1;
    }

    public String getEmpate() {
        return empate;
    }

    public void setEmpate(String empate) {
        this.empate = empate;
    }

    public String getGana2() {
        return gana2;
    }

    public void setGana2(String gana2) {
        this.gana2 = gana2;
    }

    public String getEquipo2() {
        return equipo2;
    }

    public void setEquipo2(String equipo2) {
        this.equipo2 = equipo2;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
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

    public ResultadoEnum getComprobar() {
        return comprobar;
    }

    public void setComprobar(ResultadoEnum comprobar) {
        this.comprobar = comprobar;
    }

    public int puntos(Persona persona) {
        int total = 0;
        ConfigService configService = new ConfigService();
        configService.obtenerCofiguracion();
        if (partido.resultadoPartido(equipo).equals(resultado)) {
            total += configService.getPuntosPronAcertado();
        }
        return total;
    }

    @Override
    public String toString() {
        return "ID: " + idPronostico +
                ", Persona: '" + persona + '\'' +
                ", Equipo1: '" + equipo1 + '\'' +
                ", Gana1: '" + gana1 + '\'' +
                ", Empate: '" + empate + '\'' +
                ", Gana2: '" + gana2 + '\'' +
                ", Equipo2: '" + equipo2 + '\'' + "\n";
    }
}
