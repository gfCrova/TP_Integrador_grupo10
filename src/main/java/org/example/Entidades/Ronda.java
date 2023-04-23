package org.example.Entidades;

import org.example.Services.ConfigService;

import java.util.ArrayList;
import java.util.List;

public class Ronda {

    private int id;
    private int numero;
    private String nombre;
    private List<Partido> partidos;

    public Ronda() {
    }

    public Ronda(int numero, List<Partido> partidos) {
        this.numero = numero;
        this.partidos = partidos;
    }

    public Ronda(int id, String nombre, int numero, List<Partido> partidos) {
        this.id = id;
        this.nombre = nombre;
        this.numero = numero;
        this.partidos = partidos;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public List<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos;
    }

    public int totalPuntos(List<Pronostico> pronosticos, Persona persona) {
        int i = 0;
        int total = 0;
        while (i < pronosticos.size()) {
            total += pronosticos.get(i).puntos(persona);
            i++;
        }
        return total;
    }

    @Override
    public String toString() {
        return "Ronda: " + id + ", Nombre: " + nombre + ", Fase: " + numero + "\n" +
                "Lista de Partidos: \n" + this.partidos + "\n";
    }
}
