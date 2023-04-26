package org.example;
import org.example.DAO.PartidosDAO;
import org.example.Entidades.Partido;
import org.example.Entidades.Ronda;
import org.example.Services.PronosticosService;

import java.util.HashSet;
import java.util.List;

public class MainEntrega3 {
    public static void main(String[] args) {

        PronosticosService pronosService = new PronosticosService();
        PartidosDAO partidosDAO = new PartidosDAO();
        List<Ronda> rondas = partidosDAO.obtenerRondas();

        System.out.println("\nPronósticos Deportivos \n");

        // Rondas con sus respectivos Partidos
        HashSet<Integer> hasSet = new HashSet<>();
        for (Ronda ron : rondas) {
            if (!hasSet.contains(ron.getId())) {
                hasSet.add(ron.getId());
                List<Partido> li = partidosDAO.filtrarPartidosPorClaveForanea(ron.getPartidos(), hasSet.size());
                System.out.println("Ronda: " + ron.getId() + " " + ron.getNombre() + "\n" + li + "\n");
            }
        }

        // Pronósticos
        pronosService.generarPronosticos();
    }
}
