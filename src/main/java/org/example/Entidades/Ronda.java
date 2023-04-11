package org.example.Entidades;

import org.example.Entidades.Partido;
import org.example.Entidades.Persona;
import org.example.Entidades.Pronostico;

import java.util.ArrayList;

public class Ronda {

    private String numero;
    private ArrayList<Partido> partidos;

    public Ronda() {
    }

    public Ronda(String numero, ArrayList<Partido> partidos) {
        this.numero = numero;
        this.partidos = partidos;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public ArrayList<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(ArrayList<Partido> partidos) {
        this.partidos = partidos;
    }

    public int totalPuntos(ArrayList<Pronostico> pronosticos, Persona persona) {
        int i = 0;
        int total = 0;
        while (i < pronosticos.size()) {
            total += pronosticos.get(i).puntos(persona);
            i++;
        }
        return total;
    }
}