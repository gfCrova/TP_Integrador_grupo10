package org.example.DAO;
import org.example.Entidades.Partido;
import org.example.Entidades.Pronostico;
import org.example.Entidades.Ronda;
import org.example.Services.PronosticosService;

import java.util.HashSet;
import java.util.List;

public class PruebaDAO_Entrega3 {
    public static void main(String[] args) {

        PronosticosService pronosService = new PronosticosService();
        PartidosDAO partidosDAO = new PartidosDAO();
        List<Ronda> rondas = partidosDAO.obtenerRondas();
        HashSet<Integer> hasSet = new HashSet<>();


        System.out.println("\n Pronósticos Deportivos \n");

        // Rondas con sus respectivos Partidos
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
