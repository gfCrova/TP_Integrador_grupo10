package org.example.Entidades;

import java.util.ArrayList;
import java.util.List;

public class Persona {

    private int id_persona;
    private String nombre;
    private Ronda ronda;
    private List<Pronostico> pronostico;

    public Persona() {
    }

    public Persona(int id_persona, String nombre) {
        this.id_persona = id_persona;
        this.nombre = nombre;
    }

    public Persona(int id_persona, String nombre, ArrayList<Pronostico> pronostico) {
        this.id_persona = id_persona;
        this.nombre = nombre;
        this.pronostico = pronostico;
    }

    public Persona(int id_persona, String nombre, Ronda ronda, ArrayList<Pronostico> pronosticos) {
        this.id_persona = id_persona;
        this.nombre = nombre;
        this.ronda = ronda;
        this.pronostico = pronosticos;
    }

    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Ronda getRonda() {
        return ronda;
    }

    public void setRonda(Ronda ronda) {
        this.ronda = ronda;
    }

    public List<Pronostico> getPronostico() {
        return pronostico;
    }

    public void setPronostico(List<Pronostico> pronostico) {
        this.pronostico = pronostico;
    }

    @Override
    public String toString() {
        return "ID: " + id_persona +
                ", Nombre: '" + nombre + '\'' +
                ", Ronda: " + ronda +
                ", Pronostico: " + pronostico + "\n";
    }
}
