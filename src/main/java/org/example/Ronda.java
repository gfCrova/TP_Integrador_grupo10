package org.example;

import java.util.ArrayList;

public class Ronda {

    private String numero;

    private ArrayList<Partido> partidos;

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

    public int totalPuntos() {
        return 0;
    };

    @Override
    public String toString() {
        return "Ronda {" +
                " partidos: " + partidos +
                '}';
    }
}
